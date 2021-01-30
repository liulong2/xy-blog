package com.xybbz.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xybbz.blog.entity.Blog;
import com.xybbz.blog.vo.BlogVO;
import com.xybbz.util.BaseService;

import java.util.List;

/**
 * <p>
 * 博客表 服务类
 * </p>
 *
 * @author liu
 * @since 2021-01-10
 */
public interface BlogService extends BaseService<Blog> {

    boolean addBlog(BlogVO blogVO);

    boolean removeBlog(List<Long> ids);

    boolean updateBlog(BlogVO blogVO);

    IPage<BlogVO> blogPage(BlogVO blogVO, IPage<Blog> fistPage);

    BlogVO detailedBlog(Long fistLong);

    Blog newsBlog(Long fistLong);

    IPage<BlogVO> sortListPage(String sortName, IPage<Blog> fistPage);
}
