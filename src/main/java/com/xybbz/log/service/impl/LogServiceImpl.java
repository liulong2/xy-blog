package com.xybbz.log.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xybbz.logentity.LogLocal;
import com.xybbz.mapper.LogDAO;
import com.xybbz.log.service.LogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 日志表 服务实现类
 * </p>
 *
 * @author liu
 * @since 2020-12-05
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogDAO, LogLocal> implements LogService {


}
