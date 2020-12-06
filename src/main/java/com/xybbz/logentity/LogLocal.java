package com.xybbz.logentity;

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
 * 日志表
 * </p>
 *
 * @author liu
 * @since 2020-12-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("xy_log")
@ApiModel(value="Log对象", description="日志表")
public class LogLocal implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id")
    private Long id;

    @ApiModelProperty(value = "请求ip")
    private String ip;

    @ApiModelProperty(value = "服务器ip")
    private String linxIp;

    @ApiModelProperty(value = "请求参数")
    private String params;

    @ApiModelProperty(value = "请求")
    private String metod;

    @ApiModelProperty(value = "方法名")
    private String methodName;

    @ApiModelProperty(value = "方法类")
    private String methodClass;

    @ApiModelProperty(value = "请求方式(手机...)")
    private Integer methodType;

    @ApiModelProperty(value = "请求地址")
    private String methodUrl;

    @ApiModelProperty(value = "是否删除")
    private Integer isDelete;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;


}
