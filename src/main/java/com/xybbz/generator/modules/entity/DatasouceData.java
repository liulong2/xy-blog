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
 * 数据库连接信息
 * </p>
 *
 * @author liu
 * @since 2021-01-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("xy_datasouce_data")
@ApiModel(value="DatasouceData对象", description="数据库连接信息")
public class DatasouceData extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "数据库地址")
    private String jdbcUrl;

    @ApiModelProperty(value = "账号")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "连接池")
    private String driverClassName;

}
