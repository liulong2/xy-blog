package com.xybbz.annctation;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiLog {
    /**
     * 记录自定义信息
     */
    String value() default "记录";
}
