package com.xybbz.exception;

import cn.hutool.core.util.StrUtil;
import com.xybbz.configreturn.XY;
import com.xybbz.configreturn.XYCodeEnum;
import com.xybbz.sendlogs.ExceptionLogSend;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.lang.Nullable;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * 处理异常请求参数 全局
 * @see org.springframework.boot.web.servlet.error.DefaultErrorAttributes
 * @author 刘梦龙
 */
@Slf4j
public class ParameterCheckException extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        String uri = this.getAttr(webRequest, "javax.servlet.error.request_uri");
        Integer status = this.getAttr(webRequest, "javax.servlet.error.status_code");
        Throwable error = super.getError(webRequest);
        XY result = null;
        if (error != null) {
            log.error("URL: {},error status: {}", uri, status);
            result = XY.responseFailure(XYCodeEnum.FAILURE, "系统异常:" + status);
        } else {
            log.error(StrUtil.format("URL: %s, status: %d"), error);
        }
        ExceptionLogSend.publishEvent(error,uri);
        return getMap(result);
    }


    @Nullable
    private <T> T getAttr(WebRequest webRequest, String name) {
        return (T) webRequest.getAttribute(name, RequestAttributes.SCOPE_REQUEST);
    }

    /**
     * 转换XY为map
     */
    public Map<String,Object> getMap(XY xy) {
        Class<? extends XY> aClass = xy.getClass();
        String name = aClass.getName();
        BeanMap beanMap = BeanMap.create(xy);
        return beanMap;
    }

}
