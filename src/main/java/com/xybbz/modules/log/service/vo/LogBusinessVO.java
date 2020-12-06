package com.xybbz.modules.log.service.vo;

import com.xybbz.logentity.LogBusiness;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@ApiModel(value="LogBusinessVO对象", description="启动日志表")
public class LogBusinessVO extends LogBusiness {

    private static final long serialVersionUID = 1L;


}
