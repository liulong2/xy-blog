package com.xybbz.generator.table;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 数据库表名
 *
 * @author 刘梦龙
 */
@Data
@NoArgsConstructor
public class TableNameVO {

    /**
     * 名称
     */
    private String tableName;

    /**
     * 表备注
     */
    private String tableComment;
    /**
     * 字段名称及备注
     */
}
