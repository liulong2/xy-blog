package com.xybbz.blog.service.impl;

import com.xybbz.blog.entity.Blog;
import com.xybbz.blog.mapper.BlogDAO;
import com.xybbz.blog.service.BlogService;
import com.xybbz.util.BaseServiceImpl;
import org.springframework.stereotype.Service;

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


}
