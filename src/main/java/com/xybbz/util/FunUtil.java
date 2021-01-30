package com.xybbz.util;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.db.Page;
import cn.hutool.db.sql.Direction;
import cn.hutool.db.sql.Order;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;

import java.util.ArrayList;
import java.util.Arrays;
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
        Long[] longs = ArrayUtil.newArray(Long.class, ids.length);
        for (int i = 0; i < ids.length; i++) {
            longs[i] = fistLong(ids[i]);
        }
        return longs;
    }

    /**
     * 自定义分页类转化为IPage<T>
     */
    public static <T> IPage<T> fistPage(Page page) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<T> myPage = new com.baomidou.mybatisplus.extension.plugins.pagination.Page<T>();
        myPage.setCurrent(page.getPageNumber());
        myPage.setSize(page.getPageSize());
        if (ArrayUtil.isNotEmpty(page.getOrders())) {
            List<OrderItem> orders = CollectionUtil.newArrayList();
            CollectionUtil.toList(page.getOrders()).forEach(item ->{
                OrderItem orderItem = new OrderItem();
                Direction direction = item.getDirection();
                if (direction.equals(Direction.DESC)) {
                    orderItem.setAsc(false);
                }
                orderItem.setColumn(item.getField());
                orders.add(orderItem);
            });
            myPage.setOrders(orders);
        }
        return myPage;
    }
}
