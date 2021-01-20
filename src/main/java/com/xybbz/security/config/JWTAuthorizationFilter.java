package com.xybbz.security.config;

import cn.hutool.core.collection.CollectionUtil;
import com.xybbz.body.entity.PlatformNew;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import static com.xybbz.util.TokenUtil.getPlatformNew;

/**
 * 验证成功当然就是进行鉴权了
 * 登录成功之后走此类进行鉴权操作
 *
 */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {


    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    //登陆成功后进行鉴权 生成新的token
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        String tokenHeader = request.getHeader(JwtNewUtils.TOKEN_HEADER);
        // 如果请求头中没有Authorization信息则直接放行了
        if (tokenHeader == null || !tokenHeader.startsWith(JwtNewUtils.TOKEN_PREFIX)){
            chain.doFilter(request, response);
            return;
        }

        PlatformNew platformNew = getPlatformNew(request);
        // 如果请求头中有token，则进行解析，并且设置认证信息
        SecurityContextHolder.getContext().setAuthentication(getAuthentication(tokenHeader,platformNew));

        super.doFilterInternal(request, response, chain);
    }

    // 这里从token中获取用户信息并新建一个token
    private UsernamePasswordAuthenticationToken getAuthentication(String tokenHeader,PlatformNew platformNew) {
        String token = tokenHeader.replace(JwtNewUtils.TOKEN_PREFIX, "");



        String userName = JwtNewUtils.getUsername(token,platformNew.getJwtKey());
        String role = JwtNewUtils.getUserRole(token,platformNew.getJwtKey());
        ArrayList<String> strings = CollectionUtil.toList(role.split(","));
        if (userName != null){
            return new UsernamePasswordAuthenticationToken(userName, null,
                    strings.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
        }
        return null;
    }
}
