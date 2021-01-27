package com.xybbz.blog.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.xybbz.blog.entity.Blog;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

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
@ApiModel(value="BlogVO对象", description="博客表")
public class BlogVO extends Blog {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "用户名称")
    private String userName;
    @ApiModelProperty(value = "用户头像")
    private String userIcon;
    @ApiModelProperty(value = "最后回复")
    private String lastReply;
    @ApiModelProperty(value = "最后回复时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastTime;


}
