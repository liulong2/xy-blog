package com.xybbz.blog.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.xybbz.blog.service.BlogReplyService;


import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 博客回复表 前端控制器
 * </p>
 *
 * @author liu
 * @since 2021-01-25
 */
@RestController
@Api(value = "博客回复表", tags = "博客回复表表接口")
@RequestMapping("/blog/reply")
public class BlogReplyController {
    @Autowired
    private BlogReplyService blogreplyService;

}
