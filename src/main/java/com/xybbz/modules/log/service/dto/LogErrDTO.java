package com.xybbz.modules.log.service.dto;

import com.xybbz.logentity.LogErr;
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
public class LogErrDTO extends LogErr {

    private static final long serialVersionUID = 1L;


}
