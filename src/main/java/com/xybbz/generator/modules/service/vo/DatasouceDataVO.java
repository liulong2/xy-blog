package com.xybbz.generator.modules.service.vo;

import com.xybbz.generator.modules.entity.DatasouceData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 数据库连接信息
 * </p>
 *
 * @author liu
 * @since 2021-01-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="DatasouceDataVO对象", description="数据库连接信息")
public class DatasouceDataVO extends DatasouceData {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "前缀")
    private String tablePrefix;


}
