package com.xybbz.log.service.vo;

import com.xybbz.logentity.LogApi;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@ApiModel(value="LogApiVO对象", description="api日志操作")
public class LogApiVO extends LogApi {

    private static final long serialVersionUID = 1L;


}
