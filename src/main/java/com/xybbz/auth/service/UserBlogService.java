package com.xybbz.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xybbz.auth.entity.UserBlog;
import com.xybbz.util.BaseService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author liu
 * @since 2020-12-28
 */
public interface UserBlogService extends BaseService<UserBlog> {

    boolean addUser(UserBlog userBlog);

    boolean logOut();

    boolean verification(String token);
}
