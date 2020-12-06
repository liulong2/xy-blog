package com.xybbz.configreturn;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.servlet.http.HttpServletResponse;

@Getter
@AllArgsConstructor
public enum XYCodeEnum implements IXYCode {

    /**
     * 成功消息
     */
    SUCCESS(HttpServletResponse.SC_OK,"消息成功"),
    FAILURE(HttpServletResponse.SC_BAD_REQUEST,"业务异常"),
    UN_AUTHORIZED(HttpServletResponse.SC_UNAUTHORIZED,"请求未授权"),
    INTERNAL_SERVER_ERROR(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "服务器异常"),
    NOT_FOUND(HttpServletResponse.SC_NOT_FOUND, "404:未找到请求"),
    MSG_NOT_READABLE(HttpServletResponse.SC_BAD_REQUEST, "400: 系统错误");


    /**
     * 获取编码
     */
    final int code;

    /**
     * 获取消息
     */
    final String message;
}
