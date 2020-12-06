package com.xybbz.exception;

import com.xybbz.configreturn.IXYCode;
import com.xybbz.configreturn.XYCodeEnum;
import lombok.Getter;

public class AuthException extends RuntimeException {

    private static final long serialVersionUID = -872833834848606059L;

    @Getter
    private final IXYCode ixyCode;

    public AuthException(String msg) {
        super(msg);
        this.ixyCode = XYCodeEnum.UN_AUTHORIZED;
    }

    public AuthException(IXYCode ixyCode) {
        super(ixyCode.getMessage());
        this.ixyCode = ixyCode;
    }

    public AuthException(IXYCode ixyCode, Throwable error) {
        super(error);
        this.ixyCode = ixyCode;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
