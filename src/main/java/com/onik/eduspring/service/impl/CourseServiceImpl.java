package com.onik.eduspring.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.onik.eduspring.dto.CourseDto;
import com.onik.eduspring.entity.Course;
import com.onik.eduspring.entity.PageResult;
import com.onik.eduspring.mapper.CourseMapper;
import com.onik.eduspring.service.CourseService;
import com.onik.eduspring.vo.CourseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    /**
     * 查询所有课程信息
     * @return
     */
    public PageResult<CourseVo> getAllCourse(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<CourseVo> courses =courseMapper.getAllCourse();
        PageInfo<CourseVo> p = new PageInfo<>(courses);
        return new PageResult<>(p.getTotal(),p.getList());
    }

    /**
     * 根据课程名查询课程信息
     * @param title
     * @return
     */
    public PageResult<CourseVo> getCourseByTitle(String title,Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<CourseVo> courses = courseMapper.getCourseByTitle(title);
        PageInfo<CourseVo> p = new PageInfo<>(courses);
        return new PageResult<>(p.getTotal(),p.getList());
    }

    /**
     * 添加课程信息
     * @param courseDto
     */
    @Transactional
    public void save(CourseDto courseDto) {
        Course course = new Course();
        BeanUtils.copyProperties(courseDto, course);
        course.setCreateTime(LocalDateTime.now());
        courseMapper.save(course);
    }

    /**
     * 修改课程信息
     * @param course
     */
    @Transactional
    public void update(Course course) {
        courseMapper.update(course);
    }

    /**
     * 根据id删除课程信息
     * @param id
     */
    @Transactional
    public void delById(Long id) {
        courseMapper.delById(id);
    }

    /**
     * 根据教师id查询课程信息
     * @param id
     * @return
     */
    @Override
    public List<CourseVo> getCourseByTeaId(Long id) {
        return courseMapper.getCourseByTeaId(id);
    }
}
