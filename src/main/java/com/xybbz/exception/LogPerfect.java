package com.xybbz.exception;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.xybbz.logentity.LogLocal;
import com.xybbz.util.WebServletUtil;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * 完善log内容
 */
public class LogPerfect {

    private static final String[] IP_HEADER_NAMES = new String[]{
            "x-forwarded-for",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_CLIENT_IP",
            "HTTP_X_FORWARDED_FOR"
    };

    public static void perfectLog(HttpServletRequest request, LogLocal logLocal) {
        if (ObjectUtil.isNotEmpty(request)) {
            //获得真实ip
            logLocal.setIp(getIp(request));
            logLocal.setMethodUrl(request.getRequestURI());
            logLocal.setMetod(request.getMethod());
            logLocal.setParams(WebServletUtil.getQueryString(request));
            logLocal.setLinxIp(request.getRemoteAddr());
        }
    }

    //获取ip
    private static String getIp(@NotNull HttpServletRequest request) {
        if (Objects.isNull(request)) {
            return StringPool.EMPTY;
        }
        String ip = null;
        for (String ipHeaderName : IP_HEADER_NAMES) {
            ip = request.getHeader(ipHeaderName);
            if (!(StrUtil.isBlank(ip) || "unknown".equalsIgnoreCase(ip))){
                break;
            }
        }
        if (StrUtil.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
