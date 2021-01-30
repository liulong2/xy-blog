package com.xybbz.event;

import com.xybbz.constant.LogConstant;
import com.xybbz.logentity.LogApi;
import com.xybbz.logentity.LogLocalApi;
import com.xybbz.mapper.LogApiDAO;
import com.xybbz.mapper.LogDAO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

import java.util.Map;

@Slf4j
@AllArgsConstructor
public class ApiLogListener {

    private final LogApiDAO logApiDAO;
    private final LogDAO logDAO;

    @Async
    @Order
    @EventListener(ApiLogEvent.class)
    public void saveApiLog(ApiLogEvent event) {
        Map<String, Object> source = (Map<String, Object>) event.getSource();
        LogLocalApi logLocalApi = (LogLocalApi) source.get(LogConstant.LOG);
        logDAO.insert(logLocalApi);
        LogApi logApi = logLocalApi.getLogApi(logLocalApi);
        logApiDAO.insert(logApi);
    }
}
