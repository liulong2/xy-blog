package com.xybbz.generator.modules.controller;


import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.xybbz.configreturn.XY;
import com.xybbz.generator.modules.entity.DatasouceData;
import com.xybbz.generator.modules.service.DatasouceDataService;
import com.xybbz.util.FunUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 数据库连接信息 前端控制器
 * </p>
 *
 * @author liu
 * @since 2021-01-02
 */
@RestController
@Api(value = "数据库连接信息", tags = "数据库连接信息表接口")
@RequestMapping("/datasouce/data")
public class DatasouceDataController {
    @Autowired
    private DatasouceDataService datasoucedataService;

    @ApiOperationSupport(order = 1,ignoreParameters = {"id","isDelete","updateTime","createTime"})
    @ApiOperation(value = "新增", notes = "传入data对象")
    @PostMapping("/save")
    public XY saveObj(@Valid @RequestBody DatasouceData datasouceData) {
        return XY.responseStatus(datasoucedataService.save(datasouceData));
    }

    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "代码生成",notes = "传入TablesNameIds")
    @PostMapping("/generator")
    public XY generatorObj(@ApiParam(value = "id集合", required = true) @RequestParam String ids) throws Exception {
        return XY.responseStatus(datasoucedataService.generatorObj(FunUtil.fistListLong(ids)));
    }

}
