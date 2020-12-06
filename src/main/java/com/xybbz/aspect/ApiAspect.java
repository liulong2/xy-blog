package com.xybbz.aspect;

import com.xybbz.annctation.ApiLog;
import com.xybbz.sendlogs.ApiLogSend;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class ApiAspect {
    @Pointcut("@annotation(com.xybbz.annctation.ApiLog)")
    public void apiLongs() {
    }

    @Around("@annotation(apiLog)")
    public Object sendEventApiLog(ProceedingJoinPoint point, ApiLog apiLog) throws Throwable {

        //class名称
        String className = point.getTarget().getClass().getName();
        //获取方法名称
        String methodName = point.getSignature().getName();
        //当前时间
        long nanoTime = System.currentTimeMillis();
        //方法执行
        Object result = point.proceed();
        //执行时间
        long time = System.currentTimeMillis() - nanoTime;
        //将消息发送出去
        ApiLogSend.sendApiLogEvent(methodName,className,apiLog,time);
        return result;
    }
}
