package com.xybbz.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xybbz.auth.entity.UserBlog;
import com.xybbz.auth.mapper.UserBlogDAO;
import com.xybbz.auth.service.UserBlogService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
