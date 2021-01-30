package com.xybbz.blog.service.impl;

import com.xybbz.blog.entity.Bulletin;
import com.xybbz.mapper.BulletinDAO;
import com.xybbz.blog.service.BulletinService;
import com.xybbz.enums.YesNoEnum;
import com.xybbz.util.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * <p>
 * 公告表 服务实现类
 * </p>
 *
 * @author liu
 * @since 2021-01-10
 */
@Service
public class BulletinServiceImpl extends BaseServiceImpl<BulletinDAO, Bulletin> implements BulletinService {


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean add(Bulletin bulletin) {
        Bulletin bulletinNew = lambdaQuery().eq(Bulletin::getIsEnable, YesNoEnum.YES.getCode()).one();
        if (Objects.nonNull(bulletinNew)) {
            bulletinNew.setIsEnable(YesNoEnum.NO.getCode());
            super.updateById(bulletinNew);
        }
        return super.save(bulletin);
    }

    @Override
    public Bulletin getLatest() {
        Bulletin bulletin = lambdaQuery().eq(Bulletin::getIsEnable, YesNoEnum.YES.getCode()).one();
        return bulletin;
    }
}
