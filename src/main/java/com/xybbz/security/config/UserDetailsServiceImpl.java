package com.xybbz.security.config;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xybbz.auth.entity.UserBlog;
import com.xybbz.auth.service.UserBlogService;
import com.xybbz.body.entity.Role;
import com.xybbz.body.service.RoleService;
import com.xybbz.configreturn.XY;
import com.xybbz.util.SpringUtil;
import freemarker.template.utility.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(getClass());
    //修改UserDetailsServiceImpl.java ，添加属性和setter方法 ，修改loadUserByUsername方法

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (username == null) {
            logger.error("未传入用户名");
            return null;
        }

        UserBlogService userBlogService = SpringUtil.context.getBean(UserBlogService.class);

        UserBlog userBlog = userBlogService.lambdaQuery().eq(UserBlog::getUserName, username).one();

        if (Objects.isNull(userBlog)) {
            return null;
        }

        if (StrUtil.isNotBlank(userBlog.getRoleInfo())) {

            ArrayList<String> roles = CollectionUtil.toList(userBlog.getRoleInfo().split(","));

            RoleService roleService = SpringUtil.context.getBean(RoleService.class);

            List<Long> rolesIds = roles.stream().map(Long::valueOf).collect(Collectors.toList());

            List<Role> roleList = roleService.lambdaQuery().in(Role::getId, rolesIds).list();
            // 权限集合
            /*List<GrantedAuthority> authList = new ArrayList<>();

            // 具体具有什么的权限
            for (Role role : roleList) {
                authList.add(new SimpleGrantedAuthority(String.valueOf(role.getId())));
            }*/

            JwtUser jwtUser = new JwtUser(userBlog.getUserName(), userBlog.getPassword(), roleList);
            jwtUser.setUserId(String.valueOf(userBlog.getId()));
            jwtUser.setRegTime(userBlog.getCreateTime());
            jwtUser.setEmail(StrUtil.isNotBlank(userBlog.getUserMailbox()) ? userBlog.getUserMailbox() : "");
            jwtUser.setNickName(StrUtil.isNotBlank(userBlog.getUserNickName()) ? userBlog.getUserNickName() : "");
            //1 判断用户名是否为null 如果为null 直接返回null

            //3 如果用户查不到 返回null

            //4 如果用户对象查到了 判断用户审核 是否通过 如果未通过返回null
            //5 返回user 对象 将用户名 密码 返回权限集合
            //6 框架帮助比对用户名和密码是否匹配
            return jwtUser;
        }
        return null;
    }
}
