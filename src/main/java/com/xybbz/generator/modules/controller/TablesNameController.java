package com.xybbz.generator.modules.controller;


import com.xybbz.generator.modules.service.TablesNameService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 数据库表名 前端控制器 返回
 * </p>
 *
 * @author liu
 * @since 2020-12-04
 */
@RestController
@RequestMapping("/tables/name")
@Api(value = "数据库表", tags = "数据库表名" )
public class TablesNameController {
    @Autowired
    private TablesNameService tablesnameService;

    /*@ApiOperation()
    @ApiOperationSupport(order = 1)
    public */


}
