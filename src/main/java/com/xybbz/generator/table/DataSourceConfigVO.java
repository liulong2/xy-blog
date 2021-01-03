package com.xybbz.generator.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataSourceConfigVO {

    /**
     * 数据库地址
     */
    private String url;

    /**
     * 数据库用户名
     */
    private String username = "root";

    /**
     * 数据库密码
     */
    private String password = "root";

    /**
     * 数据库链接驱动
     */
    private String driverClassName = "com.mysql.cj.jdbc.Driver";

    /**
     * 创建路径
     */
    private String dirPath;

    /**
     * 设置表名
     */
    private String tableName;
    /**
     * 表前缀
     */
    private String prefix = "xy_";
    /**
     * 作者
     */
    private String author = "liu";

    /**
     * 代码生成的包名
     */
    private String packageName = "com.xybbz";
}
