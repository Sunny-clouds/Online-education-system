package com.onik.eduspring.service;

import com.onik.eduspring.dto.StudentCourseDto;
import com.onik.eduspring.dto.StudentCourseStatusDto;
import com.onik.eduspring.entity.PageResult;
import com.onik.eduspring.vo.StudentCourseVo;

import java.util.List;

public interface StudentCourseService {
    /**
     * 获取所有学生选课信息
     * @return
     */
    PageResult<StudentCourseVo> getAll(Integer pageNum, Integer pageSize);

    /**
     * 根据学生姓名查询选课信息
     * @param nickname
     * @return
     */
    List<StudentCourseVo> getByUserName(String nickname);

    /**
     * 根据选课id删除选课信息
     * @param id
     */
    void delByUserName(Long id);

    /**
     * 添加选课信息
     * @param studentCourseDto
     */
    void save(StudentCourseDto studentCourseDto);

    /**
     * 修改选课进度
     * @param statusDto
     */
    void update(StudentCourseStatusDto statusDto);
}
