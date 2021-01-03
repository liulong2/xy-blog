package com.xybbz.generator.table;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.xybbz.generator.modules.entity.DatasouceData;
import com.xybbz.generator.modules.service.DatasouceDataService;
import com.xybbz.generator.modules.service.vo.DatasouceDataVO;
import com.xybbz.generator.modules.service.vo.TablesParamsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.w3c.dom.ls.LSInput;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Component
public class TableData {
    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 获取所连数据库 表列表
     *
     * @param datasouceData 封装对象
     * @return
     * @throws SQLException
     */
    public List<TableNameVO> getTableName(DatasouceData datasouceData, String tablePrefix)
            throws SQLException {
        DruidDataSource druidDataSource = applicationContext.getBean(DruidDataSource.class);
        if (StrUtil.isNotBlank(datasouceData.getJdbcUrl()) && StrUtil.isNotBlank(datasouceData.getUsername()) &&
                StrUtil.isNotBlank(datasouceData.getPassword()) && StrUtil.isNotBlank(datasouceData.getDriverClassName())) {
            druidDataSource.setUrl(datasouceData.getJdbcUrl());
            druidDataSource.setUsername(datasouceData.getUsername());
            druidDataSource.setPassword(datasouceData.getPassword());
            druidDataSource.setDriverClassName(datasouceData.getDriverClassName());
        }
        DruidPooledConnection connection = druidDataSource.getConnection();
        return getTables(connection, tablePrefix);
    }

    /**
     * 获取 所有数据库
     *
     * @param connection  链接
     * @param tablePrefix 表前缀
     * @return
     * @throws SQLException
     */
    private List<TableNameVO> getTables(DruidPooledConnection connection, String tablePrefix) throws SQLException {
        List<String> tableList = CollectionUtil.newArrayList();
        DatabaseMetaData metaData = connection.getMetaData();
        Statement statement = connection.createStatement();
        ResultSet tables = metaData.getTables(connection.getCatalog(), null,
                tablePrefix, new String[]{"TABLE"});
        while (tables.next()) {
            tableList.add(tables.getString("TABLE_NAME"));
        }
        List<TableNameVO> tableNameVOS = CollectionUtil.newArrayList();
        for (String s : tableList) {
            TableNameVO tableNameVO = new TableNameVO();
            tableNameVO.setTableName(s);

            String databaseProductName = connection.getConnection().getCatalog();
            ResultSet resultSet = statement.executeQuery("SELECT TABLE_NAME,TABLE_COMMENT FROM " +
                    "information_schema.TABLES WHERE table_schema=\"" + databaseProductName + "\" and table_name = \"" + s + "\"");

            while (resultSet.next()) {
                String comment = resultSet.getString("TABLE_COMMENT");
                tableNameVO.setTableComment(comment);
            }
            ResultSet rs = statement.executeQuery("show full columns from " + s);
            while (rs.next()) {
//                rs.getString("Field")
                String comment = rs.getString("Comment");
            }
            tableNameVOS.add(tableNameVO);
        }

        connection.close();
        return tableNameVOS;
    }
}
