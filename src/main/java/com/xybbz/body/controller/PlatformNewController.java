package com.xybbz.body.controller;


import com.xybbz.body.service.PlatformNewService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 平台信息表 前端控制器
 * </p>
 *
 * @author liu
 * @since 2020-12-27
 */
@RestController
@Api(value = "平台信息表", tags = "平台信息表表接口")
@RequestMapping("/platform/new")
public class PlatformNewController {

    @Autowired
    private PlatformNewService platformnewService;

}
