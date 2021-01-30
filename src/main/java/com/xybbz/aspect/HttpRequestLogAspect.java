package com.xybbz.aspect;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xybbz.log.enums.LogLevelEnum;
import com.xybbz.properties.RequestLogProperties;
import com.xybbz.util.WebServletUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.MethodParameter;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.core.annotation.SynthesizingMethodParameter;
import org.springframework.core.io.InputStreamSource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@Aspect
@AllArgsConstructor
@Configuration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@ConditionalOnProperty(value = LogLevelEnum.LOG_PROPS_PREFIX + ".enable", havingValue = "true", matchIfMissing = true)
public class HttpRequestLogAspect {

    private final RequestLogProperties requestLogProperties;

    /**
     * 通过aop环绕 来获取请求数据和返回数据
     */
    @Around("execution(!static com.xybbz.configreturn.XY *(..)) && " +
            "(@within(org.springframework.stereotype.Controller) ||" +
            "@within(org.springframework.web.bind.annotation.RestController))")
    public Object aroundParams(ProceedingJoinPoint point) throws Throwable {
        LogLevelEnum level = requestLogProperties.getLevel();
        //不打印日志,直接返回
        if (LogLevelEnum.NONE == level) {
            return point.proceed();
        }
        HttpServletRequest request = WebServletUtil.getRequest();
        //请求地址
        String requestURI = Objects.requireNonNull(request).getRequestURI();
        //获得请求方法
        String method = request.getMethod();

        //apo 执行前的打印
        //固定长度
        StringBuilder builder = StrUtil.builder(300);
        //日志参数
        List<Object> parameters = CollectionUtil.newArrayList();
        builder.append("\n\n ***************  Request 获取开始  ****************\n");

        //打印数据放进builder
        builder.append("**** {}: {}");
        parameters.add(requestURI);
        parameters.add(method);
        //将请求中的各项参数聚合到builder和list中
        printLog(point,builder,parameters);
        //打印请求头数据
        printHeaders(request,builder,parameters);
        builder.append("\n\n ***************  Request 获取结束  ****************\n");



        long nanoTime = System.nanoTime();
        log.info(builder.toString(),parameters.toArray());

        //aop切面之后的操作
        StringBuilder returnBuilder = StrUtil.builder(300);
        List<Object> returnList = CollectionUtil.newArrayList();

        returnBuilder.append("\n\n ***************  Response 获取开始  ****************\n");
        try {
            Object result = point.proceed();
            returnBuilder.append(" ***** Return *****  {}\n");
            String str = JSONUtil.toJsonPrettyStr(result);
            returnList.add(str);
            return result;
        }finally {
            long toMillis = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - nanoTime);
            returnBuilder.append("****** {}: {} ({}ms)\n");
            returnList.add(method);
            returnList.add(requestURI);
            returnList.add(toMillis);
            returnBuilder.append(" ***************  Response 获取结束  ****************");
            log.info(returnBuilder.toString(),returnList.toArray());
        }

    }

    private void printLog(ProceedingJoinPoint point, StringBuilder builder, List<Object> parameters) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        //获得参数的值
        Object[] args = point.getArgs();
        //处理参数
        Map<String, Object> map = CollectionUtil.newHashMap(20);
        Object requestBodyValue = null;
        for (int i = 0; i < args.length; i++) {

            MethodParameter methodParameter = getMethodParameter(method, i);
            PathVariable parameterAnnotation = methodParameter.getParameterAnnotation(PathVariable.class);
            if (parameterAnnotation != null) {
                continue;
            }
            //一般用在修改属性的时候
            RequestBody requestBody = methodParameter.getParameterAnnotation(RequestBody.class);
            //获取参数名称
            String parameterName = methodParameter.getParameterName();
            Object arg = args[i];
            if (requestBody != null) {
                requestBodyValue = arg;
                continue;
            }

            //处理file, request response 参数
            if (arg instanceof MultipartFile) {
                MultipartFile multipartFile = (MultipartFile) arg;
                String name = multipartFile.getName();
                String originalFilename = multipartFile.getOriginalFilename();
                map.put(name, originalFilename);
                continue;
            } else if (arg instanceof HttpServletRequest) {
                map.putAll(((HttpServletRequest) arg).getParameterMap());
                continue;
            } else if (arg instanceof WebRequest) {
                map.putAll(((WebRequest) arg).getParameterMap());
                continue;
            } else if (arg instanceof HttpServletResponse) {
                continue;
            } else if (arg instanceof List) {
                List<?> list = (List<?>) arg;
                //自动更新boolean
                AtomicBoolean atomicBoolean = new AtomicBoolean(false);
                for (Object o : list) {
                    //判断是不是多文件上传
                    if (o.getClass().getSimpleName().equalsIgnoreCase("StandardMultipartFile")) {
                        atomicBoolean.set(true);
                        break;
                    }
                }
                if (atomicBoolean.get()) {
                    map.put(parameterName, "此参数不能被序列化为json");
                    continue;
                }
            }
            RequestParam requestParam = methodParameter.getParameterAnnotation(RequestParam.class);

            String paramName = parameterName;
            if (requestParam != null && StrUtil.isNotBlank(requestParam.value())) {
                paramName = requestParam.value();
            }

            if (arg == null) {
                map.put(paramName, null);
            } else if (ClassUtil.isPrimitiveWrapper(arg.getClass())) { //判断是否为包装类
                map.put(paramName, arg);
            } else if (arg instanceof InputStream) {
                map.put(paramName, "InputStream");
            } else if (arg instanceof InputStreamSource) {
                map.put(paramName, "InputStreamSource");
            } else if (isjson(arg)) {
                // 判断模型能被 json 序列化，则添加
                map.put(paramName, arg);
            } else {
                map.put(paramName, "此参数不能序列化为json");
            }
        }
        if (CollectionUtil.isEmpty(map)) {
            builder.append("\n");
        }else {
            builder.append("参数: {}\n");
            parameters.add(JSONUtil.toJsonPrettyStr(map));
        }
        if (ObjectUtil.isNotNull(requestBodyValue)) {
            builder.append("*****参数和值*****");
            parameters.add(JSONUtil.toJsonPrettyStr(requestBodyValue));
        }
    }

    private static final ParameterNameDiscoverer PARAMETER_NAME_DISCOVERER = new DefaultParameterNameDiscoverer();

    /**
     * 获得请求中的各项参数
     * @param method
     * @param parameterIndex
     * @return
     */
    private MethodParameter getMethodParameter(Method method, int parameterIndex) {
        MethodParameter methodParameter = new SynthesizingMethodParameter(method, parameterIndex);
        methodParameter.initParameterNameDiscovery(PARAMETER_NAME_DISCOVERER);
        return methodParameter;
    }

    //判断能否被序列化
    private boolean isjson(Object o) {
        if (Objects.isNull(o)) {
            return false;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.canSerialize(o.getClass());
    }
    //打印请求头
    private void printHeaders(HttpServletRequest request, StringBuilder builder, List<Object> parameters) {
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            builder.append(" ***** 请求头内容 ***** {}: {} \n");
            parameters.add(headerName);
            parameters.add(headerValue);
        }
    }


}
