package com.xybbz.blog.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.xybbz.blog.service.BlogService;


import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 博客表 前端控制器
 * </p>
 *
 * @author liu
 * @since 2021-01-10
 */
@RestController
@Api(value = "博客表", tags = "博客表表接口")
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private BlogService blogService;

}
