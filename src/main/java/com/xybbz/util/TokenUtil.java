package com.xybbz.util;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.StrUtil;
import com.xybbz.body.entity.PlatformNew;
import com.xybbz.body.service.PlatformNewService;

import javax.servlet.http.HttpServletRequest;

import static com.xybbz.security.config.JwtNewUtils.TOKEN_PREFIX;

/**
 * 获取请求头信息
 */
public class TokenUtil {

    public static PlatformNew getPlatformNew(HttpServletRequest request) {
        String blog = request.getHeader("Blog");
        PlatformNew platformNew = new PlatformNew();
        if (StrUtil.isNotBlank(blog)) {
            String replace = blog.replace(TOKEN_PREFIX, "");
            if (StrUtil.isNotBlank(replace)) {
                String decodeStr = Base64.decodeStr(replace);
                PlatformNewService platformNewService = SpringUtil.context.getBean(PlatformNewService.class);
                platformNew = platformNewService.lambdaQuery().eq(PlatformNew::getLogo, decodeStr).one();
            }
        } else {
            platformNew.setExpirationTime(3600);
            platformNew.setJwtKey("qQwWeErRtTyYuUiIoOpPaAsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM1234567890");
            platformNew.setAuthor("token");
            platformNew.setTokenPrefix(TOKEN_PREFIX);
        }
        return platformNew;
    }
}
