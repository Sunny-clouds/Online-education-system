package com.onik.eduspring.controller.admin;

import com.onik.eduspring.dto.CourseDto;
import com.onik.eduspring.entity.Course;
import com.onik.eduspring.entity.PageResult;
import com.onik.eduspring.result.Result;
import com.onik.eduspring.service.CourseService;
import com.onik.eduspring.vo.CourseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 接收课程请求
 */
@RestController
@Slf4j
@RequestMapping("api/course")
public class CourseController {

    @Autowired
    private CourseService courseService;


    /**
     * 获取所有课程
     * @return
     */
    @GetMapping("/getAll")
    public Result getAllCourse(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        PageResult<CourseVo> courseVo =courseService.getAllCourse(pageNum, pageSize);
        return Result.success(courseVo);
    }

    /**
     * 根据课程名查询课程信息
     * @param title
     * @return
     */
    @GetMapping("/getByTitle")
    public Result getCourseByTitle(@RequestParam String title,@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        PageResult<CourseVo> courseVo = courseService.getCourseByTitle(title, pageNum, pageSize);
        return Result.success(courseVo);
    }

    /**
     * 添加课程
     * @param courseDto
     * @return
     */
    @PostMapping("/save")
    @PreAuthorize("hasAnyAuthority('admin','teacher')")
    public Result saveCourse(@RequestBody CourseDto courseDto) {
        courseService.save(courseDto);
        System.out.println("添加课程成功:" + courseDto);
        return Result.success();
    }

    /**
     * 修改课程信息
     * @param course
     * @return
     */
    @PutMapping("/updateCourse")
    @PreAuthorize("hasAnyAuthority('admin','teacher')")
    public Result updateCourse(@RequestBody Course course) {
        courseService.update(course);
        System.out.println("修改课程成功:" + course);
        return Result.success();
    }


    /**
     * 删除课程
     * @param id
     * @return
     */
    @DeleteMapping("/del/{id}")
    @PreAuthorize("hasAnyAuthority('admin','teacher')")
    public Result delCourse(@PathVariable Long id) {
        courseService.delById(id);
        System.out.println("删除课程成功:" + id);
        return Result.success();
    }

    /**
     * 根据教师id查询课程信息
     * @return
     */
    @GetMapping("/getByTeaId/{id}")
    public Result getCourseByTeaId(@PathVariable Long id){
        List<CourseVo> courseVo = courseService.getCourseByTeaId(id);
        return Result.success(courseVo);
    }

}
