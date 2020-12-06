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
 * 启动日志表
 * </p>
 *
 * @author liu
 * @since 2020-12-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="LogBusiness对象", description="启动日志表")
public class LogLocalBusiness extends LogLocal implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "日志级别")
    private String logLevel;

    @ApiModelProperty(value = "日志id")
    private Long logId;

    @ApiModelProperty(value = "日志数据")
    private String logData;

    public LogBusiness getLogBusiness(LogLocalBusiness business) {
        LogBusiness logBusiness = new LogBusiness();
        logBusiness.setLogLevel(business.getLogLevel());
        logBusiness.setLogId(business.getId());
        logBusiness.setLogData(business.getLogData());

        return logBusiness;
    }


}
