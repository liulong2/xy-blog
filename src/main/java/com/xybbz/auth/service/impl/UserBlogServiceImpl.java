package com.xybbz.auth.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xybbz.auth.entity.UserBlog;
import com.xybbz.auth.mapper.UserBlogDAO;
import com.xybbz.auth.service.UserBlogService;
import com.xybbz.body.entity.PlatformNew;
import com.xybbz.security.config.JwtNewUtils;
import com.xybbz.util.WebServletUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

import static com.xybbz.util.TokenUtil.getPlatformNew;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author liu
 * @since 2020-12-28
 */
@Service
public class UserBlogServiceImpl extends ServiceImpl<UserBlogDAO, UserBlog> implements UserBlogService {


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addUser(UserBlog userBlog) {
        userBlog.setPassword(new BCryptPasswordEncoder().encode(userBlog.getPassword()));
        return save(userBlog);
    }

    //不使用redis
    @Override
    public boolean logOut() {


        return false;
    }

    @Override
    public boolean verification(String token) {
        if (StrUtil.isBlank(token)) {
            return false;
        }
        HttpServletRequest request = WebServletUtil.getRequest();
        PlatformNew platformNew = getPlatformNew(request);
        String replace = token.replace(JwtNewUtils.TOKEN_PREFIX, "");
        boolean expiration = JwtNewUtils.isExpiration(replace, platformNew.getJwtKey());
        return !expiration;
    }
}
