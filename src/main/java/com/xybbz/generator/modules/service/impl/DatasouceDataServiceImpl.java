package com.xybbz.generator.modules.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xybbz.generator.MpGenerator;
import com.xybbz.generator.modules.entity.DatasouceData;
import com.xybbz.generator.modules.entity.TablesName;
import com.xybbz.generator.modules.mapper.DatasouceDataDAO;
import com.xybbz.generator.modules.service.DatasouceDataService;
import com.xybbz.generator.modules.service.TablesNameService;
import com.xybbz.generator.table.DataSourceConfigVO;
import com.xybbz.util.BaseEntity;
import com.xybbz.util.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * <p>
 * 数据库连接信息 服务实现类
 * </p>
 *
 * @author liu
 * @since 2021-01-02
 */
@Service
public class DatasouceDataServiceImpl extends BaseServiceImpl<DatasouceDataDAO, DatasouceData> implements DatasouceDataService {
    @Autowired
    private MpGenerator mpGenerator;
    @Autowired
    private TablesNameService tablesNameService;


    @Override
    public boolean generatorObj(List<Long> fistListLong) throws Exception {

        List<TablesName> tablesNames = tablesNameService.lambdaQuery().in(BaseEntity::getId, fistListLong).list();
        if (CollectionUtil.isEmpty(tablesNames)) {
            return false;
        }
        TablesName tablesName = tablesNames.get(0);

        //获取要生成的表名称
        List<String> collect = tablesNames.stream().map(TablesName::getTableName).collect(Collectors.toList());
        DataSourceConfigVO dataSourceConfigVO = new DataSourceConfigVO();

        //装配数据
        DatasouceData datasouceData = getById(tablesName.getDataSourceId());
        String join = CollectionUtil.join(collect, ",");
        setDataSourceConfigVO(datasouceData,dataSourceConfigVO);
        dataSourceConfigVO.setTableName(join);

        if (Objects.isNull(datasouceData)) {
            return false;
        }
        try {
            mpGenerator.generateByTables(dataSourceConfigVO);
            return true;
        }catch (Exception e) {
            return false ;
        }
    }
    private void setDataSourceConfigVO(DatasouceData datasouceData,DataSourceConfigVO dataSourceConfigVO) {
        dataSourceConfigVO.setUsername(datasouceData.getUsername());
        dataSourceConfigVO.setUrl(datasouceData.getJdbcUrl());
        dataSourceConfigVO.setPassword(datasouceData.getPassword());
        dataSourceConfigVO.setDriverClassName(datasouceData.getDriverClassName());
    }
}
