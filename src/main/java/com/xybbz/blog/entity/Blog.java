package com.xybbz.blog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.xybbz.util.BaseEntity;
import java.time.LocalTime;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

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
@TableName("xy_blog")
@ApiModel(value="Blog对象", description="博客表")
public class Blog extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "博客标题")
    private String blogTitle;

    @ApiModelProperty(value = "博客作者")
    private Long blogAuthorId;

    @ApiModelProperty(value = "博客分类(可以不选)")
    private Long blogSortId;

    @ApiModelProperty(value = "博客编码")
    private String blogCoding;

    @ApiModelProperty(value = "博客内容")
    private String blogContext;

    @ApiModelProperty(value = "最后回复时间")
    private Date blogRelayTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "添加时间")
    private Date addTime;

    @ApiModelProperty(value = "是否隐藏")
    private Integer isHide;

    @ApiModelProperty(value = "是否可回复")
    private Integer isReply;


}
