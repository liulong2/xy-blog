package com.xybbz.blog.service;

import com.xybbz.blog.entity.Bulletin;
import com.xybbz.util.BaseService;

/**
 * <p>
 * 公告表 服务类
 * </p>
 *
 * @author liu
 * @since 2021-01-10
 */
public interface BulletinService extends BaseService<Bulletin> {

    boolean add(Bulletin bulletin);

    Bulletin getLatest();
}
