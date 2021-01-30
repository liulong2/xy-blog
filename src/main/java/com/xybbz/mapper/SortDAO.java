package com.xybbz.mapper;

import com.xybbz.blog.entity.Sort;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 分类表 Mapper 接口
 * </p>
 *
 * @author liu
 * @since 2021-01-20
 */
 @Mapper
 @Repository
 @Scope("prototype")
public interface SortDAO extends BaseMapper<Sort> {

}
