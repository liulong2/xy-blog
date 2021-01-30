package com.xybbz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xybbz.logentity.LogApi;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * api日志操作 Mapper 接口
 * </p>
 *
 * @author liu
 * @since 2020-12-06
 */
 @Mapper
 @Repository
 @Scope("prototype")
public interface LogApiDAO extends BaseMapper<LogApi> {

}
