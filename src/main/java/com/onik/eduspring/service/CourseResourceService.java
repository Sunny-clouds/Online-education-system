package com.onik.eduspring.service;

import com.onik.eduspring.dto.CourseResourceDto;
import com.onik.eduspring.vo.CourseResourceVo;

import java.util.List;

public interface CourseResourceService {

    /**
     * 根据课程id获取课程资源
     * @param id
     * @return
     */
    List<CourseResourceVo> getCourseResourceById(Long id);

    /**
     * 根据id删除课程资源
     * @param id
     */
    void delCourseResourceById(Long id);

    /**
     * 上传课程资源
     * @param courseResourceDto
     */
    void save(CourseResourceDto courseResourceDto);
}
