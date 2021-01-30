package com.xybbz.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xybbz.blog.entity.BlogReply;
import com.xybbz.blog.vo.BlogReplyVO;
import com.xybbz.util.BaseService;

/**
 * <p>
 * 博客回复表 服务类
 * </p>
 *
 * @author liu
 * @since 2021-01-25
 */
public interface BlogReplyService extends BaseService<BlogReply> {

    IPage<BlogReplyVO> getByBlogId(Long fistLong, IPage<Object> fistPage);
}
