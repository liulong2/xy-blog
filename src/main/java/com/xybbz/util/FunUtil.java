package com.xybbz.util;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ArrayUtil;

import java.util.List;

public class FunUtil {

    public static Long fistLong(String obj) {
        return Long.valueOf(obj);
    }

    public static List<Long> fistListLong(String ids) {
        return CollectionUtil.toList(getList(ids.split(",")));
    }

    public static Long[] getList(String[] ids) {

        if (ArrayUtil.isEmpty(ids)) {
            return new Long[0];
        }
        Long[] longs = ArrayUtil.newArray(Long.class,ids.length);
        for (int i = 0; i < ids.length; i++) {
            longs[i] = fistLong(ids[i]);
        }
        return longs;
    }
}
