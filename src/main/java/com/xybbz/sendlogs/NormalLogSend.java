package com.xybbz.sendlogs;

import cn.hutool.core.collection.CollectionUtil;
import com.xybbz.constant.LogConstant;
import com.xybbz.event.NormalLogEvent;
import com.xybbz.exception.LogPerfect;
import com.xybbz.logentity.LogBusiness;
import com.xybbz.util.SpringUtil;
import com.xybbz.util.WebServletUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 业务日志
 */
@Slf4j
public class NormalLogSend {

    public static void sendNormal(String level, String id, String data) {
        log.info(level + "测试infor");
        HttpServletRequest request = WebServletUtil.getRequest();
        LogBusiness logBusiness = new LogBusiness();
        logBusiness.setLogLevel(level);
        logBusiness.setLogId(id);
        logBusiness.setLogData(data);
        Thread thread = Thread.currentThread();
        StackTraceElement[] stackTrace = thread.getStackTrace();
        //下标为2和3的元素包含的信息是最有用的
        if (stackTrace.length > 3) {
            logBusiness.setMethodClass(stackTrace[3].getClassName());
            logBusiness.setMethodName(stackTrace[3].getMethodName());
        }
        LogPerfect.perfectLog(request, logBusiness);
        Map<String, Object> map = CollectionUtil.newHashMap(20);
        map.put(LogConstant.LOG,logBusiness);
        SpringUtil.sendEvent(new NormalLogEvent(map));

    }
}
