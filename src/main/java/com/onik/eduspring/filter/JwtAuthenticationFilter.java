package com.onik.eduspring.filter;

import com.onik.eduspring.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

/**
 * JWT认证过滤器
 * 作用：
 * 1、每次请求都会进入该过滤器
 * 2、从请求头中获取token
 * 3、解析JWT
 * 4、获取用户角色
 * 5、交给SpringSecurity进行权限管理
 */
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain filterChain) throws ServletException, IOException {
        // 1、从请求头中获取token
        String token = request.getHeader("token");
        // 2、如果token存在则解析
        if (token != null && !token.isEmpty()) {
            try {
                // 3、解析JWT
                Claims claims = JwtUtil.parseToken(token);
                // 4、获取角色信息
                String role = (String) claims.get("role_code");
                log.info("当前用户角色:{} " ,role);
                // 5、将角色转换为SpringSecurity权限对象
                List authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(role);
                // 6、创建认证对象
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(claims, null, authorities);
                // 7、将认证信息存入SecurityContext
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (Exception e) {
                // 如果token解析失败，返回401
                response.setStatus(401);
                response.getWriter().write("token无效或已过期");
                return;
            }
        }
        // 8、放行请求
        filterChain.doFilter(request, response);
    }
}