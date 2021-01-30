package com.xybbz.log.controller;


import com.xybbz.log.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 日志表 前端控制器
 * </p>
 *
 * @author liu
 * @since 2020-12-05
 */
@RestController
@RequestMapping("/log")
public class LogController {
    @Autowired
    private LogService logService;

}
