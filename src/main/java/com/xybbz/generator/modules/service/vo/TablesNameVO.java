package com.xybbz.generator.modules.service.vo;

import com.xybbz.generator.modules.entity.TablesName;
import io.swagger.annotations.ApiModel;
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
@ApiModel(value="TablesNameVO对象", description="数据库表名")
public class TablesNameVO extends TablesName {

    private static final long serialVersionUID = 1L;


}
