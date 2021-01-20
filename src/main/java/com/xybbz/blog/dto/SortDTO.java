package com.xybbz.blog.dto;

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
public class SortDTO extends Sort {

    private static final long serialVersionUID = 1L;


}
