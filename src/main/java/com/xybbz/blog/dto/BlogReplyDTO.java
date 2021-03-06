package com.xybbz.blog.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.xybbz.blog.entity.BlogReply;

/**
 * <p>
 * 博客回复表
 * </p>
 *
 * @author liu
 * @since 2021-01-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class BlogReplyDTO extends BlogReply {

    private static final long serialVersionUID = 1L;


}
