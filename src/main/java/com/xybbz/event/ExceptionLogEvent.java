package com.xybbz.event;

import org.springframework.context.ApplicationEvent;

import java.util.Map;

public class ExceptionLogEvent extends ApplicationEvent {


    public ExceptionLogEvent(Map<String, Object> source) {
        super(source);
    }
}
