package com.xybbz.blog.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.xybbz.blog.entity.Bulletin;

/**
 * <p>
 * 公告表
 * </p>
 *
 * @author liu
 * @since 2021-01-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="BulletinVO对象", description="公告表")
public class BulletinVO extends Bulletin {

    private static final long serialVersionUID = 1L;


}
