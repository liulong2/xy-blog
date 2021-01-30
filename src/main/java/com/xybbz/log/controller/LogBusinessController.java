package com.xybbz.log.controller;


import com.xybbz.configreturn.XY;
import com.xybbz.log.service.LogBusinessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 启动日志表 前端控制器
 * </p>
 *
 * @author liu
 * @since 2020-12-06
 */
@Slf4j
@RestController
@RequestMapping("/log/business")
public class LogBusinessController {
    @Autowired
    private LogBusinessService logbusinessService;

    @GetMapping("/test")
    public XY test() {
//        int i = 1/0;
        log.info("2");
        return XY.responseData(2000);
    }

}
