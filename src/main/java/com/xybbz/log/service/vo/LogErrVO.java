package com.xybbz.log.service.vo;

import com.xybbz.logentity.LogErr;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@ApiModel(value="LogErrVO对象", description="报错日志")
public class LogErrVO extends LogErr {

    private static final long serialVersionUID = 1L;


}
