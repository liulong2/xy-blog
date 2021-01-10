package com.xybbz.blog.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.xybbz.annctation.ApiLog;
import com.xybbz.blog.entity.Bulletin;
import com.xybbz.configreturn.XY;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.xybbz.blog.service.BulletinService;


import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 公告表 前端控制器
 * </p>
 *
 * @author liu
 * @since 2021-01-10
 */
@RestController
@Api(value = "公告表", tags = "公告表表接口")
@RequestMapping("/bulletin")
public class BulletinController {
    @Autowired
    private BulletinService bulletinService;

    @ApiOperation(value = "添加", notes = "传入对象")
    @ApiOperationSupport(order = 1)
    @ApiLog("添加公告")
    @PostMapping("/add")
    public XY add(Bulletin bulletin) {
        return XY.responseStatus(bulletinService.add(bulletin));
    }

    @ApiOperation(value = "获取最新", notes = "获取最新")
    @ApiOperationSupport(order = 2)
    @ApiLog("获取最新")
    @GetMapping("/get/latest")
    public XY<Bulletin> getLatest() {
        return XY.responseData(bulletinService.getLatest());
    }

}
