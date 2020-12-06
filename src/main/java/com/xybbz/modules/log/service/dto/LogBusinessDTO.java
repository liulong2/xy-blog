package com.xybbz.modules.log.service.dto;

import com.xybbz.logentity.LogBusiness;
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
public class LogBusinessDTO extends LogBusiness {

    private static final long serialVersionUID = 1L;


}
