package com.xybbz.body.vo;

import com.xybbz.body.entity.PlatformNew;
import io.swagger.annotations.ApiModel;
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
@ApiModel(value="PlatformNewVO对象", description="平台信息表")
public class PlatformNewVO extends PlatformNew {

    private static final long serialVersionUID = 1L;


}
