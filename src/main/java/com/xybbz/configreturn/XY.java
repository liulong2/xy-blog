package com.xybbz.configreturn;

import cn.hutool.http.server.HttpServerResponse;
import com.xybbz.constant.StringConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * 返回数据,放回运行桩体
 *
 * @author 刘梦龙
 */
@ApiModel("返回消息")
public class XY<T> implements Serializable {
    @ApiModelProperty(value = "状态码", required = true)
    private int status;
    @ApiModelProperty(value = "数据", required = true)
    private T data;
    @ApiModelProperty(value = "返回消息", required = true)
    private String msg;


    /**
     * 设置数据返回
     *
     * @param data 返回数据
     * @param <T>  泛型
     * @return
     */
    public static <T> XY<T> responseData(T data) {
        return responseData(data, StringConstant.DEFAULT_SUCCESS_MSG);
    }

    /**
     * 设置数据返回
     *
     * @param data 返回数据
     * @param msg  消息
     * @param <T>  泛型
     * @return
     */
    public static <T> XY<T> responseData(T data, String msg) {
        return responseData(HttpServletResponse.SC_OK, data, msg);
    }

    /**
     * 设置数据返回
     *
     * @param status 数据状态码
     * @param data   返回数据
     * @param msg    消息
     * @param <T>    泛型 失败
     * @return
     */
    public static <T> XY<T> responseData(int status, T data, String msg) {
        return new XY<>(status, data, data == null ? StringConstant.DEFAULT_NULL_MSG : msg);
    }

    public static <T> XY<T> responseStatus(boolean happening) {
        return happening ? responseMsg(StringConstant.DEFAULT_SUCCESS_MSG) : responseFailure(StringConstant.DEFAULT_FAILURE_MSG);
    }

    public static <T> XY<T> responseFailure(String msg) {
        return new XY<>(XYCodeEnum.FAILURE, msg);
    }

    public static <T> XY<T> responseFailure(XYCodeEnum xyCodeEnum, String msg) {
        return new XY<>(xyCodeEnum, msg);
    }

    public static <T> XY<T> responseFailure(IXYCode ixyCode, String msg) {
        return new XY<T>(ixyCode, msg);
    }

    public static <T> XY<T> responseMsg(String msg) {
        return new XY<T>(XYCodeEnum.SUCCESS, msg);
    }

    public XY(XYCodeEnum xyCode, String msg) {
        this(xyCode, null, msg);
    }

    public XY(XYCodeEnum xyCode, T data) {
        this(xyCode, data, xyCode.getMessage());
    }

    public XY(IXYCode ixyCode, String msg) {
        this(ixyCode, null, msg);
    }

    public XY(IXYCode ixyCode, T data, String msg) {
        this(ixyCode.getCode(), data, msg);
    }

    public XY(XYCodeEnum xyCode, T data, String msg) {
        this(xyCode.getCode(), data, msg);
    }

    public XY(int status, T data, String msg) {
        this.status = status;
        this.data = data;
        this.msg = msg;
    }

    public XY() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "XY(" +
                "status='" + status + '\'' +
                ", data='" + data + '\'' +
                ", msg='" + msg + '\'' +
                ')';
    }
}
