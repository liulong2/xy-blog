package com.xybbz.generator.modules.service.dto;

import com.xybbz.generator.modules.entity.TablesName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 数据库表名
 * </p>
 *
 * @author liu
 * @since 2020-12-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TablesNameDTO extends TablesName {

    private static final long serialVersionUID = 1L;


}
