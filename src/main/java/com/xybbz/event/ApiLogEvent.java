package com.xybbz.event;

import org.springframework.context.ApplicationEvent;

public class ApiLogEvent extends ApplicationEvent {
    public ApiLogEvent(Object source) {
        super(source);
    }
}
