package com.xybbz.auth.controller;


import com.xybbz.auth.service.UserBlogService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author liu
 * @since 2020-12-28
 */
@RestController
@Api(value = "用户表", tags = "用户表表接口")
@RequestMapping("/user/blog")
public class UserBlogController {
    @Autowired
    private UserBlogService userblogService;

}
