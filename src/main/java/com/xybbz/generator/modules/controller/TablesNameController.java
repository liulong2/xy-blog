package com.xybbz.generator.modules.controller;


import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.xybbz.configreturn.XY;
import com.xybbz.generator.modules.entity.DatasouceData;
import com.xybbz.generator.modules.service.TablesNameService;
import com.xybbz.generator.modules.service.vo.DatasouceDataVO;
import com.xybbz.generator.modules.service.vo.TablesParamsVO;
import com.xybbz.util.FunUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;

/**
 * <p>
 * 数据库表名 前端控制器
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

    @ApiOperation(value = "获取数据库列表")
    @ApiOperationSupport(order = 1)
    @GetMapping("/list")
    public XY getTableNames(TablesParamsVO tablesParamsVO) throws SQLException {
        return XY.responseData(tablesnameService.getTableNames(tablesParamsVO));
    }

    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "删除属性", notes = "传入id集合")
    @PostMapping("/delete")
    public XY deleteData(@ApiParam(value = "id集合", required = true) @RequestParam String ids) {
        return XY.responseStatus(tablesnameService.deleteData(FunUtil.fistListLong(ids)));
    }


}
