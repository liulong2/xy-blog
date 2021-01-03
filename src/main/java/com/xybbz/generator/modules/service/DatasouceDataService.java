package com.xybbz.generator.modules.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xybbz.generator.modules.entity.DatasouceData;
import com.xybbz.util.BaseService;

import java.util.List;

/**
 * <p>
 * 数据库连接信息 服务类
 * </p>
 *
 * @author liu
 * @since 2021-01-02
 */
public interface DatasouceDataService extends BaseService<DatasouceData> {

    boolean generatorObj(List<Long> fistListLong) throws Exception;
}
