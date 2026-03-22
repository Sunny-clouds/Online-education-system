package com.onik.eduspring.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.onik.eduspring.entity.PageResult;
import com.onik.eduspring.mapper.AdminMapper;
import com.onik.eduspring.mapper.StudentCourseMapper;
import com.onik.eduspring.service.AdminService;
import com.onik.eduspring.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private StudentCourseMapper studentCourseMapper;

    /**
     * 设置用户身份
     * @param id
     * @param role
     */
    @Override
    public void setRole(Long id, Integer role) {
        adminMapper.setRole(id, role);
    }

    /**
     * 根据名字查询用户
     * @param nickname
     * @return
     */
    @Override
    public UserVo getByNickName(String nickname) {
        return adminMapper.getByNickName(nickname);
    }

    /**
     * 根据id删除用户
     * @param id
     */
    @Override
    @Transactional
    public void delById(Long id) {
        studentCourseMapper.delByStudentId(id);
        adminMapper.delById(id);
    }
    /**
     * 分页查询所有用户
     * @return
     */
    public PageResult<UserVo> getAllUsers(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<UserVo> users = adminMapper.getAllUsers();
        PageInfo<UserVo> p = new PageInfo<>(users);
        return new PageResult<UserVo>(p.getTotal(),p.getList());
    }

    /**
     * 设置账号状态
     * @param id
     * @param status
     */
    @Override
    public void setStatus(Long id, Integer status) {
        adminMapper.setStatus(id, status);
    }

}
