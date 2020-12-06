package com.xybbz.logentity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 报错日志
 * </p>
 *
 * @author liu
 * @since 2020-12-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="LogErr对象", description="报错日志")
public class LogLocalErr extends LogLocal implements Serializable  {

    private static final long serialVersionUID = 1L;

   /* @ApiModelProperty(value = "主键id")
    @TableId(value = "error_id")
    private Long errorId;*/

    @ApiModelProperty(value = "堆内存地址")
    private String ramAddr;

    @ApiModelProperty(value = "请求地址")
    private String url;

    @ApiModelProperty(value = "异常信息")
    private String errorMessage;

    @ApiModelProperty(value = "异常文件名")
    private String errorFile;

    @ApiModelProperty(value = "代码行数")
    private Integer lineNumber;

    @ApiModelProperty(value = "异常名")
    private String errorName;

    @ApiModelProperty(value = "日志id")
    private Long logId;

    public LogErr getLogErr(LogLocalErr logLocalErr) {
        LogErr logErr = new LogErr();
        logErr.setRamAddr(logLocalErr.getRamAddr());
        logErr.setUrl(logLocalErr.getUrl());
        logErr.setErrorMessage(logLocalErr.getErrorMessage());
        logErr.setErrorFile(logLocalErr.getErrorFile());
        logErr.setLineNumber(logLocalErr.getLineNumber());
        logErr.setErrorName(logLocalErr.getErrorName());
        logErr.setLogId(logLocalErr.getLogId());
        return logErr;
    }

}
