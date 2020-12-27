package com.xybbz.generator;

import cn.hutool.core.collection.CollectionUtil;

import java.util.List;

public interface PageConstant {

    //生成包位置
    String PACKAGE_NAME = "com.xybbz";

    //基础类字段
    List<String> BASIS = CollectionUtil.newArrayList("Id","isDelete","updateTime","createTime");
}
