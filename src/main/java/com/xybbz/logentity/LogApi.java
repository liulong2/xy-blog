package com.xybbz.logentity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * api日志操作
 * </p>
 *
 * @author liu
 * @since 2020-12-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("xy_log_api")
@ApiModel(value="LogApi对象", description="api日志操作")
public class LogApi implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "日志id")
    private Long logId;

    @ApiModelProperty(value = "class名称")
    private String className;

    @ApiModelProperty(value = "方法名称")
    private String methodName;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "运行时间")
    private Long runTime;


}
