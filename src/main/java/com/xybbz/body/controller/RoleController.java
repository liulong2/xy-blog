package com.xybbz.body.controller;


import com.xybbz.body.service.RoleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户角色表 前端控制器
 * </p>
 *
 * @author liu
 * @since 2020-12-28
 */
@RestController
@Api(value = "用户角色表", tags = "用户角色表表接口")
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

}
