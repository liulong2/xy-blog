package com.xybbz.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xybbz.auth.entity.UserBlog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author liu
 * @since 2020-12-28
 */
 @Mapper
 @Repository
 @Scope("prototype")
public interface UserBlogDAO extends BaseMapper<UserBlog> {

}
