package com.xybbz.sendlogs;

import cn.hutool.core.collection.CollectionUtil;
import com.xybbz.annctation.ApiLog;
import com.xybbz.constant.LogConstant;
import com.xybbz.constant.StringConstant;
import com.xybbz.event.ApiLogEvent;
import com.xybbz.exception.GlobalExceptiom;
import com.xybbz.exception.LogPerfect;
import com.xybbz.logentity.LogLocalApi;
import com.xybbz.util.SpringUtil;
import com.xybbz.util.WebServletUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * api event发送
 */
public class ApiLogSend {

    public static void sendApiLogEvent(String methodName, String methodClass, ApiLog apiLog, long time) {
        HttpServletRequest request = WebServletUtil.getRequest();
        LogLocalApi logLocalApi = new LogLocalApi();
        logLocalApi.setTitle(apiLog.value());
        logLocalApi.setRunTime(time);
        logLocalApi.setClassName(methodClass);
        logLocalApi.setMethodName(methodName);
        LogPerfect.perfectLog(request,logLocalApi);
        Map<String,Object> map = CollectionUtil.newHashMap(20);
        map.put(LogConstant.LOG,logLocalApi);
        SpringUtil.sendEvent(new ApiLogEvent(map));
    }
}
