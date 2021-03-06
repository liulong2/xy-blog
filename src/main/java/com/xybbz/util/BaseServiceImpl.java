package com.xybbz.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xybbz.enums.YesNoEnum;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * @author 刘梦龙
 * @param <M>
 * @param <T>
 */
public class BaseServiceImpl<M extends BaseMapper<T>,T extends BaseEntity> extends ServiceImpl<M, T>
        implements BaseService<T> {

    @Override
    public boolean save(T entity) {
        updateEntity(entity);
        return super.save(entity);
    }

    @Override
    public boolean updateById(T entity) {
        updateEntity(entity);
        return super.updateById(entity);
    }

    public boolean updateBatchById(List<T> entityList,int batchSize) {
        entityList.forEach(this::updateEntity);
        return super.updateBatchById(entityList,batchSize);
    }

    public boolean saveOrUpdateBatch(List<T> entityList, int batchSize) {
        entityList.forEach(this::updateEntity);
        return super.saveOrUpdateBatch(entityList,batchSize);
    }

    @Override
    public boolean saveOrUpdate(T entity) {
        updateEntity(entity);
        return super.saveOrUpdate(entity);
    }

    public boolean deleteList(List<Long> ids) {
        List<T> baseEntities = CollectionUtil.newArrayList();
        ids.forEach(id ->{
            Class<T> tClass = currentModelClass();
            T baseEntity = BeanUtils.instantiateClass(tClass);
            baseEntity.setId(id);
            baseEntity.setUpdateTime(DateUtil.date());
            baseEntities.add(baseEntity);
        });

        return super.updateBatchById(baseEntities) && removeByIds(ids);
    }

    public T updateEntity(T entity) {
        entity.setIsDelete(YesNoEnum.NO.getCode());

        if (entity.getId() == null) {
            entity.setIsDelete(YesNoEnum.NO.getCode());
            entity.setCreateTime(DateUtil.date());
        }
        entity.setUpdateTime(DateUtil.date());
        return entity;
    }

}
