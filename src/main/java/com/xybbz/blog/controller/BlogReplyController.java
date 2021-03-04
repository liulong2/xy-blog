package com.xybbz.blog.controller;

import cn.hutool.db.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.xybbz.annctation.ApiLog;
import com.xybbz.blog.entity.BlogReply;
import com.xybbz.blog.vo.BlogReplyVO;
import com.xybbz.configreturn.XY;
import com.xybbz.util.FunUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.xybbz.blog.service.BlogReplyService;


import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 博客回复表 前端控制器
 * </p>
 *
 * @author liu
 * @since 2021-01-25
 */
@RestController
@Api(value = "博客回复表", tags = "博客回复表表接口")
@RequestMapping("/blog/reply")
public class BlogReplyController {
    @Autowired
    private BlogReplyService blogreplyService;
    //添加回复的需要更新blog表回复时间

    @ApiOperation(value = "获取回复列表", notes = "根据数据获得列表")
    @ApiOperationSupport(order = 1)
    @ApiLog("根据主题获取回复列表")
    public XY<IPage<BlogReplyVO>> getByBlogId(String blogId, Page page) {
        return XY.responseData(blogreplyService.getByBlogId(FunUtil.fistLong(blogId), FunUtil.fistPage(page)));
    }


}
