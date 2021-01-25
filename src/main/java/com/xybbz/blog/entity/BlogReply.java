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
 * 博客回复表
 * </p>
 *
 * @author liu
 * @since 2021-01-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("xy_blog_reply")
@ApiModel(value="BlogReply对象", description="博客回复表")
public class BlogReply extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "回复上级id")
    private Long replyPid;

    @ApiModelProperty(value = "博客id")
    private Long blogId;

    @ApiModelProperty(value = "回复人")
    private Long replyUserId;

    @ApiModelProperty(value = "回复类型(博客回复,回复内容回复)")
    private Integer replyType;

    @ApiModelProperty(value = "回复内容")
    private String replyContext;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "回复时间")
    private Date replyTime;



}
