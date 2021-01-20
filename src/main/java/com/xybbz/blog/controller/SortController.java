package com.xybbz.blog.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.xybbz.annctation.ApiLog;
import com.xybbz.blog.entity.Sort;
import com.xybbz.blog.vo.SortVO;
import com.xybbz.configreturn.XY;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.xybbz.blog.service.SortService;


import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Objects;

/**
 * <p>
 * 分类表 前端控制器
 * </p>
 *
 * @author liu
 * @since 2021-01-20
 */
@RestController
@Api(value = "分类表", tags = "分类表表接口")
@RequestMapping("/sort")
public class SortController {
    @Autowired
    private SortService sortService;

    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "添加", notes = "传入分类对象")
    @ApiLog("添加分类")
    @GetMapping("/save")
    public XY save(@Valid @RequestBody SortVO sortVO) {
        return XY.responseStatus(sortService.save(sortVO));
    }

    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "根据类型获取数据", notes = "传入类型")
    @ApiLog("根据类型获取数据")
    @GetMapping("/data")
    public XY<SortVO> data(SortVO sortVO) {
        Sort sort = Objects.requireNonNull(sortService.getOne(new LambdaQueryWrapper<>(sortVO)));
        return XY.responseData(BeanUtil.copyProperties(sort, SortVO.class));
    }

}
