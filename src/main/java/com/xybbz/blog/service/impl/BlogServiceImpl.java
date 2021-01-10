package com.xybbz.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xybbz.blog.entity.Blog;
import com.xybbz.blog.mapper.BlogDAO;
import com.xybbz.blog.service.BlogService;
import com.xybbz.blog.vo.BlogVO;
import com.xybbz.util.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 博客表 服务实现类
 * </p>
 *
 * @author liu
 * @since 2021-01-10
 */
@Service
public class BlogServiceImpl extends BaseServiceImpl<BlogDAO, Blog> implements BlogService {


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addBlog(BlogVO blogVO) {
        return super.save(blogVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeBlog(List<Long> ids) {
        return super.deleteList(ids);
    }

    @Override
    @Transactional
    public boolean updateBlog(BlogVO blogVO) {
        return super.updateById(blogVO);
    }

    @Override
    public IPage<Blog> blogPage(BlogVO blogVO, IPage<Blog> fistPage) {
        IPage<Blog> page = page(fistPage, new LambdaQueryWrapper<>(blogVO));
        return page;
    }
}
