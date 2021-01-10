package com.xybbz.auth.controller;


import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.xybbz.annctation.ApiLog;
import com.xybbz.auth.entity.UserBlog;
import com.xybbz.auth.service.UserBlogService;
import com.xybbz.configreturn.XY;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
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

    @ApiLog(value = "添加账号")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "添加账号",notes = "传入各种信息")
    @PostMapping("/add/user")
    public XY addUser(@Validated @RequestBody UserBlog userBlog) {
        return XY.responseStatus(userblogService.addUser(userBlog));
    }

    @ApiLog("退出登陆")
    @ApiOperation(value = "退出登陆")
    @ApiOperationSupport(order = 2)
    @PostMapping("/login/out")
    public XY logOut() {
        return XY.responseStatus(userblogService.logOut());
    }

    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "验证token")
    @PostMapping("/verification")
    public XY verification(String token) {
        return XY.responseStatus(userblogService.verification(token));
    }

}
