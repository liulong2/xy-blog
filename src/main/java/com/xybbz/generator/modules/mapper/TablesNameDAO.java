package com.xybbz.generator.modules.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xybbz.generator.modules.entity.TablesName;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 数据库表名 Mapper 接口
 * </p>
 *
 * @author liu
 * @since 2020-12-04
 */
 @Mapper
 @Repository
 @Scope("prototype")
public interface TablesNameDAO extends BaseMapper<TablesName> {

}
