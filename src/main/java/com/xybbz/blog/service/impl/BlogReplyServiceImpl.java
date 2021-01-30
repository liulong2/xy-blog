package com.xybbz.blog.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xybbz.blog.entity.BlogReply;
import com.xybbz.blog.vo.BlogReplyVO;
import com.xybbz.mapper.BlogReplyDAO;
import com.xybbz.blog.service.BlogReplyService;
import com.xybbz.util.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 博客回复表 服务实现类
 * </p>
 *
 * @author liu
 * @since 2021-01-25
 */
@Service
public class BlogReplyServiceImpl extends BaseServiceImpl<BlogReplyDAO, BlogReply> implements BlogReplyService {


    @Override
    public IPage<BlogReplyVO> getByBlogId(Long fistLong, IPage<Object> fistPage) {

        return null;
    }
}
