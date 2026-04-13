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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * 根据用户名、密码查询用户
     * @param dto
     * @return
     */
    @Override
    public UserLoginVo login(UserLoginDto dto) {
        User user = userMapper.getUserByUserName(dto.getUsername());
        if (user == null || !passwordEncoder.matches(dto.getPassword(), user.getPassword())){
            throw new RuntimeException("用户名或密码错误");
        }
        String role;
        switch (user.getRole()) {
            case 1:
                role = "admin";
                break;
            case 2:
                role = "teacher";
                break;
            case 3:
                role = "student";
                break;
            default:
                role = "unknown";
        }
        //生成JWT令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("role_code", role);
        String token = JwtUtil.generateToken(claims);
        return new UserLoginVo(user.getId(), user.getUsername(),user.getNickname(),user.getEmail(),user.getPhone(),role,user.getAvatar(),token);
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
            throw new RuntimeException("用户已存在");
        }
        user = new User();
        BeanUtils.copyProperties(userDto, user);
        // 密码加密
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        user.setPassword(encodedPassword);
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
        if (!passwordEncoder.matches(userPasswordDto.getOldPassword(), oldPassword)) {
            return Result.error("旧密码错误");
        }
        String newPassword = passwordEncoder.encode(userPasswordDto.getPassword());
        userPasswordDto.setPassword(newPassword);
        userMapper.updateById(userPasswordDto);
        return Result.success();
    }

}
