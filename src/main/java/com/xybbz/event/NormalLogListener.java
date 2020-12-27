package com.xybbz.event;

import com.xybbz.constant.LogConstant;
import com.xybbz.logentity.LogBusiness;
import com.xybbz.logentity.LogLocalBusiness;
import com.xybbz.modules.log.mapper.LogBusinessDAO;
import com.xybbz.modules.log.mapper.LogDAO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

import java.util.Map;

@Slf4j
@AllArgsConstructor
public class NormalLogListener {

    private final LogDAO logDAO;
    private final LogBusinessDAO logBusinessDAO;

    @Async
    @Order
    @EventListener(NormalLogEvent.class)
    public void saveNormalLog(NormalLogEvent event) {
        Map<String, Object> source = (Map<String, Object>) event.getSource();
        LogLocalBusiness logLocalBusiness = (LogLocalBusiness) source.get(LogConstant.LOG);
        logDAO.insert(logLocalBusiness);
        LogBusiness logBusiness = logLocalBusiness.getLogBusiness(logLocalBusiness);
        logBusinessDAO.insert(logBusiness);
    }


}
