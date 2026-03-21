package com.onik.eduspring.service;

import com.onik.eduspring.dto.UserDto;
import com.onik.eduspring.dto.UserLoginDto;
import com.onik.eduspring.dto.UserPasswordDto;
import com.onik.eduspring.entity.User;
import com.onik.eduspring.result.Result;
import com.onik.eduspring.vo.UserLoginVo;

import java.util.List;

public interface UserService {


    /**
     * 用户登陆
     * @param userLoginDto
     * @return
     */
    UserLoginVo login(UserLoginDto userLoginDto);

    /**
     * 修改用户
     * @param userDto
     */
    void update(UserDto userDto);

    /**
     * 用户注册
     * @param userDto
     */
    void save(UserDto userDto);

    /**
     * 获取所有教师信息
     * @return
     */
    List<User> getAllTeacher();

    /**
     * 修改密码
     * @param userPasswordDto
     */
    Result setPassword(UserPasswordDto userPasswordDto);
}
