package com.xybbz.generator.modules.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xybbz.generator.modules.entity.TablesName;
import com.xybbz.generator.modules.service.vo.DatasouceDataVO;
import com.xybbz.generator.modules.service.vo.TablesParamsVO;

import java.sql.SQLException;
import java.util.List;

/**
 * <p>
 * 数据库表名 服务类
 * </p>
 *
 * @author liu
 * @since 2020-12-04
 */
public interface TablesNameService extends IService<TablesName> {

    List<TablesName> getTableNames(TablesParamsVO paramsVO) throws SQLException;

    boolean deleteData(List<Long> fistListLong);
}
