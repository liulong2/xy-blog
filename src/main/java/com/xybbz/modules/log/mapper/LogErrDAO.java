package com.xybbz.modules.log.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xybbz.logentity.LogErr;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 报错日志 Mapper 接口
 * </p>
 *
 * @author liu
 * @since 2020-12-05
 */
 @Mapper
 @Repository
 @Scope("prototype")
public interface LogErrDAO extends BaseMapper<LogErr> {

}
