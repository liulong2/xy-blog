package com.xybbz.modules.log.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xybbz.logentity.LogApi;
import com.xybbz.modules.log.mapper.LogApiDAO;
import com.xybbz.modules.log.service.LogApiService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * api日志操作 服务实现类
 * </p>
 *
 * @author liu
 * @since 2020-12-06
 */
@Service
public class LogApiServiceImpl extends ServiceImpl<LogApiDAO, LogApi> implements LogApiService {


}
