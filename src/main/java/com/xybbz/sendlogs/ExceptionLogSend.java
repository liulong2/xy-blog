package com.xybbz.sendlogs;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import com.xybbz.event.ExceptionLogEvent;
import com.xybbz.constant.LogConstant;
import com.xybbz.exception.LogPerfect;
import com.xybbz.logentity.LogLocalErr;
import com.xybbz.util.SpringUtil;
import com.xybbz.util.WebServletUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 日志异常消息发送
 */
public class ExceptionLogSend {

    /**
     * 发送Springevent java.lang.Integer cannot be cast to java.lang.String
     * @param err 异常
     * @param uri 请求地址
     */
    public static void publishEvent(Throwable err, String uri) {
        HttpServletRequest request = WebServletUtil.getRequest();
        LogLocalErr logErr =  new LogLocalErr();
        logErr.setUrl(uri);
        if (ObjectUtil.isNotNull(err)) {
            logErr.setRamAddr(ExceptionUtil.stacktraceToOneLineString(err));
            logErr.setErrorName(err.getClass().getName());
            logErr.setErrorMessage(err.getMessage());
            StackTraceElement[] stackElements = ExceptionUtil.getStackElements();
            if (ArrayUtil.isNotEmpty(stackElements)) {
                StackTraceElement stackElement = ExceptionUtil.getStackElement(0);
                logErr.setMethodName(stackElement.getMethodName());
                logErr.setMethodClass(stackElement.getClassName());
                logErr.setErrorFile(stackElement.getFileName());
                logErr.setLineNumber(stackElement.getLineNumber());
            }
        }
        LogPerfect.perfectLog(request,logErr);
        //20个字段
        Map<String,Object> event = CollectionUtil.newHashMap(20);
        event.put(LogConstant.LOG,logErr);
        SpringUtil.sendEvent(new ExceptionLogEvent(event));
    }
}
