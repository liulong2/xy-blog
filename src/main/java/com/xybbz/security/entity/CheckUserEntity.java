package com.xybbz.security.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *  登陆信息
 * </p>
 *
 * @author liu
 * @since 2020-05-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CheckUserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 密码
     */
    private String password;


}
