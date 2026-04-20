package com.onik.eduspring.controller.user;

import com.onik.eduspring.dto.UserDto;
import com.onik.eduspring.dto.UserLoginDto;
import com.onik.eduspring.dto.UserPasswordDto;
import com.onik.eduspring.entity.User;
import com.onik.eduspring.result.Result;
import com.onik.eduspring.service.UserService;
import com.onik.eduspring.vo.UserLoginVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 接收用户请求
 */
@RestController
@Slf4j
@RequestMapping("/api/user")
@Tag(name = "用户模块")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户注册
     * @param userDto
     */
    @PostMapping("/register")
    @Operation(summary = "用户注册")
    public Result register(@RequestBody UserDto userDto) {
        userService.save(userDto);
        log.info("用户注册成功:{}", userDto);
        return Result.success();
    }

    /**
     * 用户登录
     * @param userLoginDto
     * @return
     */
    @PostMapping("/login")
    @Operation(summary = "用户登录")
    public Result login(@RequestBody UserLoginDto userLoginDto){
        log.info("用户登录中...:{}", userLoginDto);
        UserLoginVo user = userService.login(userLoginDto);
        if(user != null){
            log.info("用户登录成功:{}", user);
            return Result.success(user);
        }
        log.error("用户登录失败:{}", userLoginDto);
        return Result.error("用户名或密码错误");
    }

    /**
     * 修改用户信息
     * @param userDto
     */
    @PutMapping("/update")
    @Operation(summary = "修改用户信息")
    public Result updateUser(@RequestBody UserDto userDto) {
        userService.update(userDto);
        log.info("修改用户信息成功:{}", userDto);
        return Result.success();
    }

    /**
     * 获取所有老师
     * @return
     */
    @GetMapping("/teacherList")
    @Operation(summary = "获取所有老师")
    public Result getTeacherList() {
        List<User> list = userService.getAllTeacher();
        log.info("获取所有老师成功:{}", list.size());
        return Result.success(list);
    }

    /**
     * 修改密码
     * @return
     */
    @PostMapping("/setPassword")
    @Operation(summary = "修改密码")
    private Result setPassword(@RequestBody UserPasswordDto userPasswordDto){
        log.info("修改密码:{}", userPasswordDto);
        return userService.setPassword(userPasswordDto);
    }

}
