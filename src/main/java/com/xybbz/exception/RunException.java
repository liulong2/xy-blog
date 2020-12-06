package com.xybbz.exception;

import com.xybbz.configreturn.IXYCode;
import com.xybbz.configreturn.XYCodeEnum;
import lombok.Getter;

public class RunException extends RuntimeException {

    private static final long serialVersionUID = -8590560173884217616L;
    @Getter
    private final IXYCode ixyCode;

    public RunException(String message) {
        super(message);
        this.ixyCode = XYCodeEnum.FAILURE;
    }

    public RunException(IXYCode ixyCode) {
        super(ixyCode.getMessage());
        this.ixyCode = ixyCode;
    }

    public RunException(IXYCode ixyCode, Throwable cause) {
        super(cause);
        this.ixyCode = ixyCode;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
