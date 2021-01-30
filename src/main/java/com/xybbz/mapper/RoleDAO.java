package com.xybbz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xybbz.body.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 用户角色表 Mapper 接口
 * </p>
 *
 * @author liu
 * @since 2020-12-28
 */
 @Mapper
 @Repository
 @Scope("prototype")
public interface RoleDAO extends BaseMapper<Role> {

}
