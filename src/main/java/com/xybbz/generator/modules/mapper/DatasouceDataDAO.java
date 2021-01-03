package com.xybbz.generator.modules.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xybbz.generator.modules.entity.DatasouceData;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 数据库连接信息 Mapper 接口
 * </p>
 *
 * @author liu
 * @since 2021-01-02
 */
 @Mapper
 @Repository
 @Scope("prototype")
public interface DatasouceDataDAO extends BaseMapper<DatasouceData> {

}
