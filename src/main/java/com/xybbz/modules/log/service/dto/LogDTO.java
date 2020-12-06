package com.xybbz.modules.log.service.dto;

import com.xybbz.logentity.LogLocal;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
public class LogDTO extends LogLocal {

    private static final long serialVersionUID = 1L;


}
