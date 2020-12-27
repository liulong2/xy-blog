package com.xybbz.auth.vo;

import com.xybbz.auth.entity.UserBlog;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author liu
 * @since 2020-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "UserBlogVO对象", description = "用户表")
public class UserBlogVO extends UserBlog {

    private static final long serialVersionUID = 1L;


}
