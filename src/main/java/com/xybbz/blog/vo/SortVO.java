package com.xybbz.blog.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.xybbz.blog.entity.Sort;

/**
 * <p>
 * 分类表
 * </p>
 *
 * @author liu
 * @since 2021-01-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="SortVO对象", description="分类表")
public class SortVO extends Sort {

    private static final long serialVersionUID = 1L;


}
