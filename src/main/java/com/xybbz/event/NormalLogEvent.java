package com.xybbz.event;

import org.springframework.context.ApplicationEvent;

public class NormalLogEvent extends ApplicationEvent {

    public NormalLogEvent(Object source) {
        super(source);
    }
}
