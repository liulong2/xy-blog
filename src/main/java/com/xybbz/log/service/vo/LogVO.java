package com.xybbz.log.service.vo;

import com.xybbz.logentity.LogLocal;
import io.swagger.annotations.ApiModel;
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
@ApiModel(value="LogVO对象", description="日志表")
public class LogVO extends LogLocal {

    private static final long serialVersionUID = 1L;


}
