package com.xybbz.generator.table;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class TableData {
    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 获取所连数据库 表列表
     * @param jdbcUrl 数据库链接地址
     * @param username 用户名
     * @param password 密码
     * @param driverClassName 数据库链接方式
     * @param tablePrefix 表前缀 为null表示所有的表
     * @return
     * @throws SQLException
     */
    public List<String> getTableName(String jdbcUrl, String username, String password, String driverClassName,
                                     String tablePrefix)
            throws SQLException {
        DruidDataSource druidDataSource = applicationContext.getBean(DruidDataSource.class);
        druidDataSource.setUrl(jdbcUrl);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        druidDataSource.setDriverClassName(driverClassName);
        DruidPooledConnection connection = druidDataSource.getConnection();
        return getTables(connection, tablePrefix);
    }

    /**
     * 获取 所有数据库
     * @param connection 链接
     * @param tablePrefix 表前缀
     * @return
     * @throws SQLException
     */
    private List<String> getTables(DruidPooledConnection connection, String tablePrefix) throws SQLException {
        List<String> tableList = CollectionUtil.newArrayList();
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet tables = metaData.getTables(connection.getCatalog(), "%",
                tablePrefix, new String[]{"TABLE"});
        while (tables.next()) {
            tableList.add(tables.getString("TABLE_NAME"));
        }
        // todo 2020-12-4 21:03 需要从数据库中查询并且存入数据库
//        CollectionUtil.unionDistinct(tableList);
        return tableList;
    }
}
