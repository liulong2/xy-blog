package com.xybbz.body.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.xybbz.util.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 平台信息表
 * </p>
 *
 * @author liu
 * @since 2020-12-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("xy_platform_new")
@ApiModel(value="PlatformNew对象", description="平台信息表")
public class PlatformNew extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "过期时长")
    private Integer expirationTime;
    //带扩充字段


}
