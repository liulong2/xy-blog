package com.xybbz.mapper;

import com.xybbz.blog.entity.Bulletin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 公告表 Mapper 接口
 * </p>
 *
 * @author liu
 * @since 2021-01-10
 */
 @Mapper
 @Repository
 @Scope("prototype")
public interface BulletinDAO extends BaseMapper<Bulletin> {

}
