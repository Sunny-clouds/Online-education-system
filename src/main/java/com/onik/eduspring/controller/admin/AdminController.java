package com.onik.eduspring.controller.admin;

import com.onik.eduspring.entity.PageResult;
import com.onik.eduspring.result.Result;
import com.onik.eduspring.service.AdminService;
import com.onik.eduspring.vo.UserVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 接收管理员请求
 */
@RestController
@Slf4j
@RequestMapping("/api/admin")
@Tag(name = "管理员模块")
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * 分页查询所有用户
     * @return
     */
    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/selUser")
    @Operation(summary = "分页查询所有用户")
    public Result getAllUsers(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        PageResult<UserVo> users =adminService.getAllUsers(pageNum, pageSize);
        log.info("获取所有用户成功:{}", users.getTotal());
        return Result.success(users);
    }

    /**
     * 根据id删除用户
     * @param id
     */
    @PreAuthorize("hasAnyAuthority('admin','teacher')")
    @DeleteMapping("/delById/{id}")
    @Operation(summary = "根据id删除用户")
    public Result delUserById(@PathVariable Long id) {
        adminService.delById(id);
        log.info("删除用户成功:{}", id);
        return Result.success();
    }

    /**
     * 根据用户名查询用户
     * @param nickname
     * @return
     */
    @PreAuthorize("hasAnyAuthority('admin','teacher')")
    @GetMapping("/getByNickName")
    @Operation(summary = "根据用户名查询用户")
    public Result getByNickName(@RequestParam String nickname) {
        UserVo user = adminService.getByNickName(nickname);
        log.info("查询用户成功:{}", user);
        return Result.success(user);
    }

    /**
     * 设置用户身份
     * @param id
     * @param role
     */
    @PreAuthorize("hasAuthority('admin')")
    @PutMapping("/setRole/{id}/{role}")
    @Operation(summary = "设置用户身份")
    public Result setRole(@PathVariable Long id,@PathVariable Integer role) {
        adminService.setRole(id, role);
        log.info("设置用户身份成功:{} — {}", id, role);
        return Result.success();
    }

    /**
     * 设置用户状态
     * @param id
     * @param status
     * @return
     */
    @PreAuthorize("hasAuthority('admin')")
    @PutMapping("/setStatus/{id}/{status}")
    @Operation(summary = "设置用户状态")
    public Result setStatus(@PathVariable Long id,@PathVariable Integer status) {
        adminService.setStatus(id, status);
        log.info("设置用户状态成功:{} — {}", id, status);
        return Result.success();
    }

}
