package com.xybbz.security.config;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.xybbz.body.entity.PlatformNew;
import com.xybbz.configreturn.XY;
import com.xybbz.security.entity.CheckUserEntity;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

import static com.xybbz.util.TokenUtil.getPlatformNew;


/**
 * 验证用户名密码正确后，生成一个token，并将token返回给客户端
 * 该类继承自UsernamePasswordAuthenticationFilter，重写了其中的2个方法 ,
 * attemptAuthentication：接收并解析用户凭证。
 * successfulAuthentication：用户成功登录后，这个方法会被调用，我们在这个方法里生成token并返回。
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        super.setFilterProcessesUrl("/auth/login");
    }

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        // 从输入流中获取到登录的信息
        try {

            Set<Map.Entry<String, String[]>> entries = request.getParameterMap().entrySet();
            Map<String, String> newParameter = CollectionUtil.newHashMap();
            for (Map.Entry<String, String[]> entry : entries) {
                String key = entry.getKey();
                String value = entry.getValue()[0];
                newParameter.put(key, value);
            }
            //通过过滤器获得相应的数据,可以在此处理相应的逻辑(暂时预留出来的)
            CheckUserEntity loginUser = JSON.parseObject(JSON.toJSONString(newParameter), CheckUserEntity.class);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(loginUser.getUserName(), loginUser.getPassword());
            Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            return authenticate;
        } catch (Exception e) {
            throw new Exception("用户信息异常");
        }
    }

    // 成功验证后调用的方法 reason authentication failed
    // 如果验证成功，就生成token并返回 标识
    @SneakyThrows
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain, Authentication authResult)
            throws IOException, ServletException {

        JwtUser user = (JwtUser) authResult.getPrincipal();
        System.out.println("jwtUser:" + user.toString());

        PlatformNew platformNew = getPlatformNew(request);

        //根据权限获得token
        String token = JwtNewUtils.createToken(user, platformNew);
        if (StrUtil.isBlank(token)) {
            throw new Exception("登陆过期");
        }
        //也可以不传权限
        //String token = TestJwtUtils.createToken(jwtUser.getUsername(), false);
        // 返回创建成功的token
        // 但是这里创建的token只是单纯的token
        // 按照jwt的规定，最后请求的时候应该是 `Bearer token`
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        String tokenStr = platformNew.getTokenPrefix() + token;
        //待修改请求头
        response.setHeader(JwtNewUtils.TOKEN_HEADER, tokenStr);
        //添加返回值
        JwtUser principal = (JwtUser) authResult.getPrincipal();
        principal.setTOKEN(tokenStr);

        response.getWriter().write(JSON.toJSONString(XY.responseData(principal)));
    }

    //认证失败
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        response.getWriter().write("authentication failed, reason: " + failed.getMessage());
    }
}
