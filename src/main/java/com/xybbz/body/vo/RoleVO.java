package com.xybbz.body.vo;

import com.xybbz.body.entity.Role;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户角色表
 * </p>
 *
 * @author liu
 * @since 2020-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="RoleVO对象", description="用户角色表")
public class RoleVO extends Role {

    private static final long serialVersionUID = 1L;


}
