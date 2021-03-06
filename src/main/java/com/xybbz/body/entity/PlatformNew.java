package com.xybbz.body.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 平台信息表
 * </p>
 *
 * @author liu
 * @since 2020-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("xy_platform_new")
@ApiModel(value="PlatformNew对象", description="平台信息表")
public class PlatformNew implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "过期时长")
    private Integer expirationTime;

    @ApiModelProperty(value = "令牌前缀")
    private String tokenPrefix;

    @ApiModelProperty(value = "JWT秘钥")
    private String jwtKey;

    @ApiModelProperty(value = "作者")
    private String author;

    @ApiModelProperty(value = "标识")
    private String logo;

    @ApiModelProperty(value = "是否删除")
    private Integer isDelete;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;


}
