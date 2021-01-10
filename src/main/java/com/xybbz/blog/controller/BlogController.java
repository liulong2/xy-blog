package com.xybbz.blog.controller;

import cn.hutool.db.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.xybbz.annctation.ApiLog;
import com.xybbz.blog.entity.Blog;
import com.xybbz.blog.vo.BlogVO;
import com.xybbz.configreturn.XY;
import com.xybbz.util.FunUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.xybbz.blog.service.BlogService;


import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 博客表 前端控制器
 * </p>
 *
 * @author liu
 * @since 2021-01-10
 */
@RestController
@Api(value = "博客表", tags = "博客表表接口")
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private BlogService blogService;

    /**
     * 添加
     * @param blogVO 文档视图层
     * @return
     */
    @ApiOperation(value = "添加", notes = "传入文档BlogVO")
    @ApiOperationSupport(order = 1, includeParameters = {"id", "isDeleted", "updateTime","createTime"})
    @ApiLog("添加文档")
    @PostMapping("/add")
    public XY addBlog(BlogVO blogVO) {
        return XY.responseStatus(blogService.addBlog(blogVO));
    }

    @ApiOperation(value = "删除文档", notes = "传入主键ids")
    @ApiOperationSupport(order = 2)
    @ApiLog("删除")
    @PostMapping("/remove")
    public XY removeBlog(String ids) {
        return XY.responseStatus(blogService.removeBlog(FunUtil.fistListLong(ids)));
    }

    @ApiOperation(value = "修改文档", notes = "传入对象")
    @ApiOperationSupport(order = 3)
    @ApiLog("修改文档")
    @PostMapping("/update")
    public XY updateBlog(BlogVO blogVO) {
        return XY.responseStatus(blogService.updateBlog(blogVO));
    }

    @ApiOperation(value = "获取列表", notes = "查询条件")
    @ApiOperationSupport(order = 4)
    @ApiLog("获取列表")
    public XY<IPage<Blog>> blogPage(@RequestBody BlogVO blogVO, Page page) {
        return XY.responseData(blogService.blogPage(blogVO,FunUtil.fistPage(page)));
    }



}
