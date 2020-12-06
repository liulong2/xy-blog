package com.xybbz.generator.modules.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 数据库表名
 * </p>
 *
 * @author liu
 * @since 2020-12-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("xy_tables_name")
@ApiModel(value="TablesName对象", description="数据库表名")
public class TablesName implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "table_id", type = IdType.AUTO)
    private String tableId;

    private String tableName;

    private String driverClassName;

    private String tablePrefix;

    private String tableUrl;

    @ApiModelProperty(value = "是否删除")
    private Integer isDelete;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;


}
