package com.xybbz.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xybbz.blog.entity.Blog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 博客表 Mapper 接口
 * </p>
 *
 * @author liu
 * @since 2021-01-10
 */
@Mapper
@Repository
@Scope("prototype")
public interface BlogDAO extends BaseMapper<Blog> {

    List<Blog> selectBySortName(@Param("sortName") String sortName, IPage<Blog> page);
}
