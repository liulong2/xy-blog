package com.xybbz.util;

import cn.hutool.core.text.StrBuilder;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.google.common.base.Charsets;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;

public class WebServletUtil {

    /**
     * 获得请求
     */
    public static HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        return requestAttributes == null ? null : ((ServletRequestAttributes) requestAttributes).getRequest();
    }

    /**
     * 获取get请求或者post请求中的参数
     */
    public static String getQueryString(HttpServletRequest request) {
        String queryString = request.getQueryString();
        if (StrUtil.isNotBlank(queryString)) {
            return new String(queryString.getBytes(Charsets.ISO_8859_1), Charsets.UTF_8)
                    .replace("&amp;", "&").replace("%22", "\"");
        }

        String characterEncoding = request.getCharacterEncoding();
        if (StrUtil.isBlank(characterEncoding)) {
            characterEncoding = StringPool.UTF_8;
        }
        try {
            byte[] bytes = getRequestParameter(request.getInputStream()).getBytes();
            String trim = new String(bytes, characterEncoding).trim();
            if (StrUtil.isBlank(trim)) {
                StrBuilder strBuilder = StrUtil.strBuilder();
                Enumeration<String> parameterNames = request.getParameterNames();
                while (parameterNames.hasMoreElements()) {
                    String key = parameterNames.nextElement();
                    String value = request.getParameter(key);
                    StrUtil.concat(true, strBuilder, key, "=", value, "&");
                }
                trim = StrUtil.removeSuffix(strBuilder.toString(), "&");
            }
            return trim.replaceAll("&amp;", "&");
        } catch (IOException e) {
            e.printStackTrace();
            return StrUtil.EMPTY;
        }
    }

    public static String getRequestParameter(ServletInputStream inputStream) {
        StrBuilder strBuilder = StrUtil.strBuilder();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String line;
            while (ObjectUtil.isNotEmpty(line = bufferedReader.readLine())) {
                strBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return strBuilder.toString();

    }
}
