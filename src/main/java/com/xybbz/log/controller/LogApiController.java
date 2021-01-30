package com.xybbz.log.controller;


import com.xybbz.log.service.LogApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * api日志操作 前端控制器
 * </p>
 *
 * @author liu
 * @since 2020-12-06
 */
@RestController
@RequestMapping("/log/api")
public class LogApiController {
    @Autowired
    private LogApiService logapiService;

}
