package com.xybbz.util;

import cn.hutool.core.text.StrBuilder;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.google.common.base.Charsets;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;

public class WebServletUtil {
    private static int z = 0;

    /**
     * 获得请求
     */
    public static HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        return requestAttributes == null ? null : ((ServletRequestAttributes) requestAttributes).getRequest();
    }

    /**
     * 获取get请求或者post请求中的参数
     */
    public static String getQueryString(HttpServletRequest request) {
        String queryString = request.getQueryString();
        if (StrUtil.isNotBlank(queryString)) {
            return new String(queryString.getBytes(Charsets.ISO_8859_1), Charsets.UTF_8)
                    .replace("&amp;", "&").replace("%22", "\"");
        }

        String characterEncoding = request.getCharacterEncoding();
        if (StrUtil.isBlank(characterEncoding)) {
            characterEncoding = StringPool.UTF_8;
        }
        try {
            String trim = "";
            if (z == 0) {
                byte[] bytes = getRequestParameter(request.getInputStream()).getBytes();
                trim = new String(bytes, characterEncoding).trim();
                z++;
            }

            if (StrUtil.isBlank(trim)) {
                StrBuilder strBuilder = StrUtil.strBuilder();
                Enumeration<String> parameterNames = request.getParameterNames();
                while (parameterNames.hasMoreElements()) {
                    String key = parameterNames.nextElement();
                    String value = request.getParameter(key);
                    strBuilder.append(StrUtil.concat(true, key, "=", value, "&"));
                }
                trim = StrUtil.removeSuffix(strBuilder.toString(), "&");
            }else {
                z = 0;
            }
            return trim.replaceAll("&amp;", "&");
        } catch (IOException e) {
            e.printStackTrace();
            return StrUtil.EMPTY;
        }
    }

    public static String getRequestParameter(ServletInputStream inputStream) {
        StrBuilder strBuilder = StrUtil.strBuilder();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String line;
            while (ObjectUtil.isNotEmpty(line = bufferedReader.readLine())) {
                strBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedReader != null) {
                try {
                    if (z == 2) {
                        z = 0;
                    }else {
                        z++;
                    }

                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return strBuilder.toString();

    }
}
