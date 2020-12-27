package com.xybbz.body.dto;

import com.xybbz.body.entity.PlatformNew;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 平台信息表
 * </p>
 *
 * @author liu
 * @since 2020-12-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PlatformNewDTO extends PlatformNew {

    private static final long serialVersionUID = 1L;


}
