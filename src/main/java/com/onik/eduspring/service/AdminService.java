package com.onik.eduspring.service;

import com.onik.eduspring.entity.PageResult;
import com.onik.eduspring.vo.UserVo;

public interface AdminService {

    /**
     * 设置用户身份
     * @param id
     * @param role
     */
    void setRole(Long id, Integer role);

    /**
     * 根据名字查询用户
     * @param nickname
     * @return
     */
    UserVo getByNickName(String nickname);

    /**
     * 根据id删除用户
     * @param id
     */
    void delById(Long id);

    /**
     * 获取所有用户
     * @return
     */
    PageResult<UserVo> getAllUsers(Integer pageNum, Integer pageSize);

    /**
     * 设置账号状态
     * @param id
     * @param status
     */
    void setStatus(Long id, Integer status);
}
