package com.xybbz.body.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xybbz.body.entity.PlatformNew;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 平台信息表 Mapper 接口
 * </p>
 *
 * @author liu
 * @since 2020-12-27
 */
@Mapper
@Repository
@Scope("prototype")
public interface PlatformNewDAO extends BaseMapper<PlatformNew> {

}
