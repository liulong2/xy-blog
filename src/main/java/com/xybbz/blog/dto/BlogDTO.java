package com.xybbz.blog.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.xybbz.blog.entity.Blog;

/**
 * <p>
 * 博客表
 * </p>
 *
 * @author liu
 * @since 2021-01-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class BlogDTO extends Blog {

    private static final long serialVersionUID = 1L;


}
