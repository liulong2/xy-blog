package com.xybbz.log.controller;


import com.xybbz.log.service.LogErrService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 报错日志 前端控制器
 * </p>
 *
 * @author liu
 * @since 2020-12-05
 */
@RestController
@RequestMapping("/log/err")
public class LogErrController {
    @Autowired
    private LogErrService logerrService;

}
