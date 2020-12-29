package com.xybbz.exception;

import cn.hutool.core.util.StrUtil;
import com.xybbz.configreturn.XY;
import com.xybbz.configreturn.XYCodeEnum;
import com.xybbz.sendlogs.ExceptionLogSend;
import com.xybbz.util.WebServletUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.Servlet;
import java.util.Objects;

/**
 * @ConditionalOnClass 当前项目引用了servlet和dispatchServlet的时候才会使用当前配置类
 */

@Slf4j
@Order
@Configuration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@ConditionalOnClass({Servlet.class, DispatcherServlet.class})
@ControllerAdvice
public class GlobalExceptiom {

    /**
     * 400报错
     * @param e 报错
     * @return 返回报错信息
     */
    @ExceptionHandler(RunException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public XY handleError(RunException e) {
        log.error("系统错误", e);
        return XY.responseFailure(e.getIxyCode(), e.getMessage());
    }

    /**
     * 401报错
     * @param e 报错
     * @return 返回报错信息
     */
    @ExceptionHandler(AuthException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public XY handleError(AuthException e) {
        log.error("认证错误", e);
        return XY.responseFailure(e.getIxyCode(), e.getMessage());
    }


    /**
     * 500 其他异常
     * @param e 异常
     * @return 返回报错信息
     */
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public XY handleError(Throwable e) {
        ExceptionLogSend.publishEvent(e, Objects.requireNonNull(WebServletUtil.getRequest()).getRequestURI());
        return XY.responseFailure(XYCodeEnum.INTERNAL_SERVER_ERROR,
                (StrUtil.isEmpty(e.getMessage()) ? XYCodeEnum.INTERNAL_SERVER_ERROR.getMessage() : e.getMessage()));
    }

    /*@ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public XY handleError(NoHandlerFoundException e) {
        log.error("404没找到请求:{}", e.getMessage());
        return XY.responseFailure(XYCodeEnum.MSG_NOT_READABLE, e.getMessage());
    }*/


}
