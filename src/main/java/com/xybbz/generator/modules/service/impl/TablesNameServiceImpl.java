package com.xybbz.generator.modules.service.impl;

import com.xybbz.generator.modules.entity.DatasouceData;
import com.xybbz.generator.modules.entity.TablesName;
import com.xybbz.mapper.TablesNameDAO;
import com.xybbz.generator.modules.service.DatasouceDataService;
import com.xybbz.generator.modules.service.TablesNameService;
import com.xybbz.generator.modules.service.vo.TablesNameVO;
import com.xybbz.generator.modules.service.vo.TablesParamsVO;
import com.xybbz.generator.table.TableData;
import com.xybbz.generator.table.TableNameVO;
import com.xybbz.util.BaseServiceImpl;
import com.xybbz.util.FunUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 数据库表名 服务实现类
 * </p>
 *
 * @author liu
 * @since 2020-12-04
 */
@Service
public class TablesNameServiceImpl extends BaseServiceImpl<TablesNameDAO, TablesName> implements TablesNameService {
    @Autowired
    private TableData tableData;
    @Autowired
    private DatasouceDataService datasouceDataService;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<TablesName> getTableNames(TablesParamsVO paramsVO) throws SQLException {
        DatasouceData datasouceData = datasouceDataService.getById(paramsVO.getDataId());
        if (Objects.isNull(datasouceData)) {
            return null;
        }

        List<TableNameVO> tableName = tableData.getTableName(datasouceData, paramsVO.getTablePrefix());
        tableName.forEach(r -> {
            TablesNameVO tablesName = new TablesNameVO();
            tablesName.setTableName(r.getTableName());
            tablesName.setDataSourceId(FunUtil.fistLong(paramsVO.getDataId()));
            tablesName.setTablePrefix(paramsVO.getTablePrefix());
            tablesName.setTableRemake(r.getTableComment());
            TablesName one = lambdaQuery().eq(TablesName::getDataSourceId, FunUtil.fistLong(paramsVO.getDataId()))
                    .eq(TablesName::getTableName, r.getTableName()).one();
            if (Objects.isNull(one)) {
                save(tablesName);
            }
        });

        return lambdaQuery().eq(TablesName::getDataSourceId, FunUtil.fistLong(paramsVO.getDataId())).list();
    }

    @Override
    public boolean deleteData(List<Long> fistListLong) {
        return super.deleteList(fistListLong);
    }

}
