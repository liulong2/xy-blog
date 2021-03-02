package com.xybbz.auth.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.xybbz.util.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@TableName("xy_user_blog")
@ApiModel(value="UserBlog对象", description="用户表")
public class UserBlog extends BaseEntity {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "头像")
    private String usrAvatar;

    @ApiModelProperty(value = "登陆名称")
    private String userName;

    @ApiModelProperty(value = "用户昵称")
    private String userNickName;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "性别")
    private Integer userGender;

    @ApiModelProperty(value = "邮箱")
    private String userMailbox;

    @ApiModelProperty(value = "手机号")
    private String userPhone;

    @ApiModelProperty(value = "所在公司")
    private String userCompany;

    @ApiModelProperty(value = "职业")
    private String userOccupation;

    @ApiModelProperty(value = "地区")
    private String userRegion;

    @ApiModelProperty(value = "权限信息")
    private String authInfo;

    @ApiModelProperty(value = "角色信息")
    private String roleInfo;



}
