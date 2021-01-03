package com.xybbz.generator.modules.service.dto;

import com.xybbz.generator.modules.entity.DatasouceData;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 数据库连接信息
 * </p>
 *
 * @author liu
 * @since 2021-01-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DatasouceDataDTO extends DatasouceData {

    private static final long serialVersionUID = 1L;


}
