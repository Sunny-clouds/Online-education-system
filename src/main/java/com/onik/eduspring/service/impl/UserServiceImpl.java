package com.onik.eduspring.service.impl;

import com.onik.eduspring.dto.UserDto;
import com.onik.eduspring.dto.UserLoginDto;
import com.onik.eduspring.dto.UserPasswordDto;
import com.onik.eduspring.entity.User;
import com.onik.eduspring.mapper.UserMapper;
import com.onik.eduspring.result.Result;
import com.onik.eduspring.service.UserService;
import com.onik.eduspring.util.JwtUtil;
import com.onik.eduspring.vo.UserLoginVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 根据用户名、密码查询用户
     * @param dto
     * @return
     */
    @Override
    public UserLoginVo login(UserLoginDto dto) {
        UserLoginVo user = userMapper.getByUserName(dto);
        if (user != null){
            //生成JWT令牌
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", user.getId());
            claims.put("role_code", user.getRoleCode());
            String token = JwtUtil.generateToken(claims);
            return new UserLoginVo(user.getId(), user.getUsername(),user.getNickname(),user.getEmail(),user.getPhone(),user.getRoleCode(),user.getAvatar(),token);
        }
        return null;

    }

    /**
     * 修改用户
     * @param userDto
     */
    @Override
    @Transactional
    public void update(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        user.setUpdateTime(LocalDateTime.now());
        log.info("修改用户信息成功:{}", userDto);
        userMapper.update(user);
    }

    /**
     * 用户注册
     * @param userDto
     */
    @Transactional
    @Override
    public void save(UserDto userDto) {
        User user = userMapper.getUserByUserName(userDto.getUsername());
        if (user != null) {
            return;
        }
        log.info("用户注册成功:{}", userDto);
        user = new User();
        BeanUtils.copyProperties(userDto, user);
        user.setCreateTime(LocalDateTime.now());
        user.setRole(3);
        userMapper.save(user);
    }

    /**
     * 获取所有老师
     * @return
     */
    @Override
    public List<User> getAllTeacher() {
        List<User> list = userMapper.getAllTeacher();
        return list;
    }

    /**
     * 修改密码
     * @param userPasswordDto
     */
    @Override
    @Transactional
    public Result setPassword(UserPasswordDto userPasswordDto) {
        String oldPassword = userMapper.getUserById(userPasswordDto.getId());
        if (!oldPassword.equals(userPasswordDto.getOldPassword())){
            return Result.error("旧密码错误");
        }
        userMapper.updateById(userPasswordDto);
        return Result.success();
    }

}
