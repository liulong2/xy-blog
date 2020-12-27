package com.xybbz.util;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xybbz.body.entity.PlatformNew;
import com.xybbz.body.mapper.PlatformNewDAO;

public class BaseServiceImpl<M extends BaseMapper<T>,T extends BaseEntity> extends ServiceImpl<M, T>
        implements BaseService<T> {

    public boolean save(T entity) {

        return super.save(entity);
    }





    public void updateEntity(T entity) {

    }

}
