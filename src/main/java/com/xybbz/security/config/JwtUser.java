package com.xybbz.security.config;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xybbz.body.entity.Role;
import com.xybbz.security.entity.CheckUserEntity;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class JwtUser implements UserDetails {

    private String userId;
    private String userName;
    private String password;
    private String nickName;
    private boolean enabled = false;
    private List<Role> roles;
    private String email;
    private String userface;
    private Timestamp regTime;


    public JwtUser(CheckUserEntity checkUserEntity, List<Role> roles) {
        this.userName = checkUserEntity.getUserName();
        this.password = checkUserEntity.getPassword();
        this.roles = roles;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(String.valueOf(role.getId())));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() { // 帐户是否过期
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() { // 帐户是否被冻结
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() { // 帐号是否可用
        return true;
    }

   /* @Override
    public String toString() {
        return "JwtUser{" +
                "id=" + userId +
                ", username='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", authorities=" + authorities +
                '}';
    }*/
}
