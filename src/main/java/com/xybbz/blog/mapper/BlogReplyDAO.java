package com.xybbz.blog.mapper;

import com.xybbz.blog.entity.BlogReply;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 博客回复表 Mapper 接口
 * </p>
 *
 * @author liu
 * @since 2021-01-25
 */
 @Mapper
 @Repository
 @Scope("prototype")
public interface BlogReplyDAO extends BaseMapper<BlogReply> {

}
