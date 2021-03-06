package com.xybbz.generator.modules.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xybbz.util.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 数据库表名
 * </p>
 *
 * @author liu
 * @since 2021-01-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("xy_tables_name")
@ApiModel(value="TablesName对象", description="数据库表名")
public class TablesName extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "数据库id")
    private Long dataSourceId;

    @ApiModelProperty(value = "数据库表名")
    private String tableName;

    @ApiModelProperty(value = "前缀")
    private String tablePrefix;

    @ApiModelProperty(value = "数据库表备注")
    private String tableRemake;


}
