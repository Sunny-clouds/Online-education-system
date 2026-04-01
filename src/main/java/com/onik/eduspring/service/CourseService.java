package com.onik.eduspring.service;

import com.onik.eduspring.dto.CourseDto;
import com.onik.eduspring.entity.Course;
import com.onik.eduspring.entity.PageResult;
import com.onik.eduspring.vo.CourseVo;

import java.util.List;

public interface CourseService {


    /**
     * 获取所有课程信息
     * @return
     */
    PageResult<CourseVo> getAllCourse(Integer pageNum, Integer pageSize);

    /**
     * 根据课程名查询课程信息
     * @param title
     * @return
     */
    PageResult<CourseVo> getCourseByTitle(String title,Integer pageNum, Integer pageSize);

    /**
     * 添加课程信息
     * @param courseDto
     */
    void save(CourseDto courseDto);

    /**
     * 修改课程信息
     * @param course
     */
    void update(Course course);

    /**
     *根据id删除课程信息
     * @param id
     */
    void delById(Long id);

    /**
     * 根据教师id查询课程信息
     * @param id
     * @return
     */
    List<CourseVo> getCourseByTeaId(Long id);
}
