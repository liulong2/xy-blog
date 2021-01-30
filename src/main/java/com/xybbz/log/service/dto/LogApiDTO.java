package com.xybbz.log.service.dto;

import com.xybbz.logentity.LogApi;
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
public class LogApiDTO extends LogApi {

    private static final long serialVersionUID = 1L;


}
