package com.onik.eduspring.util;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 上下文工具类
 * 用于获取当前登录用户信息
 */
public class BaseContext {

    /**
     * 获取当前用户ID
     * @return 用户ID
     */
    public static Long getUserId() {
        Claims claims = (Claims) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return claims.get("id", Long.class);
    }

    ///**
    // * 获取当前用户角色
    // * @return 角色代码
    // */
    //public static String getRoleCode() {
    //    Claims claims = (Claims) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    //    return claims.get("role_code", String.class);
    //}
    //
    ///**
    // * 获取当前用户的Claims对象
    // * @return Claims对象
    // */
    //public static Claims getClaims() {
    //    return (Claims) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    //}
}
