package com.xybbz.event;

import com.xybbz.constant.LogConstant;
import com.xybbz.logentity.LogErr;
import com.xybbz.logentity.LogLocalErr;
import com.xybbz.mapper.LogDAO;
import com.xybbz.mapper.LogErrDAO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

import java.util.Map;

/**
 * 异步接收报错信息
 */
@Slf4j
@AllArgsConstructor
public class ExceptionLogListener {

    private final LogErrDAO logErrDAO;
    private final LogDAO logDAO;

    @Async
    @Order
    @EventListener(ExceptionLogEvent.class)
    public void saveExceptionLog(ExceptionLogEvent event) {
        Map<String, Object> source = (Map<String, Object>)event.getSource();
        LogLocalErr logLocalErr = (LogLocalErr) source.get(LogConstant.LOG);
        //保存
        LogErr logErr = logLocalErr.getLogErr(logLocalErr);


        logDAO.insert(logLocalErr);
        logErr.setLogId(logLocalErr.getId());

        logErrDAO.insert(logErr);
    }
}
