package com.xybbz.generator.modules.service.vo;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Api(value = "获得当前数据库表名")
public class TablesParamsVO {

    @ApiModelProperty(value = "数据库信息id")
    private String dataId;
    @ApiModelProperty(value = "表前缀")
    private String tablePrefix;

}
