package com.onik.eduspring.config;

import com.onik.eduspring.filter.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * SpringSecurity配置类
 */
@Configuration
@EnableMethodSecurity   // 开启方法级权限控制，例如 @PreAuthorize
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 关闭csrf（前后端分离项目通常关闭）
                .csrf(csrf -> csrf.disable())
                // 配置接口权限
                .authorizeHttpRequests(auth -> auth
                        // 登录注册接口放行
                        .requestMatchers("/api/user/login", "/api/user/register").permitAll()
                        // 其他所有接口必须登录
                        .anyRequest().authenticated()
                )
                // 在SpringSecurity过滤器链中添加JWT过滤器
                .addFilterBefore(new JwtAuthenticationFilter(),UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}