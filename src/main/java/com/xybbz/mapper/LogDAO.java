package com.xybbz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xybbz.logentity.LogLocal;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 日志表 Mapper 接口
 * </p>
 *
 * @author liu
 * @since 2020-12-05
 */
 @Mapper
 @Repository
 @Scope("prototype")
public interface LogDAO extends BaseMapper<LogLocal> {

}
