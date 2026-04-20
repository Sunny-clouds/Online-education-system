package com.onik.eduspring.controller.admin;

import com.onik.eduspring.dto.CourseDto;
import com.onik.eduspring.entity.Course;
import com.onik.eduspring.entity.PageResult;
import com.onik.eduspring.result.Result;
import com.onik.eduspring.service.CourseService;
import com.onik.eduspring.vo.CourseVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "课程管理")
public class CourseController {

    @Autowired
    private CourseService courseService;

    /**
     * 获取所有课程
     * @return
     */
    @GetMapping("/getAll")
    @Operation(summary = "获取所有课程")
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
    @Operation(summary = "根据课程名查询课程信息")
    public Result getCourseByTitle(@RequestParam String title,@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        PageResult<CourseVo> courseVo = courseService.getCourseByTitle(title, pageNum, pageSize);
        log.info("获取课程信息:{}", courseVo.getTotal());
        return Result.success(courseVo);
    }

    /**
     * 添加课程
     * @param courseDto
     * @return
     */
    @PostMapping("/save")
    @PreAuthorize("hasAnyAuthority('admin','teacher')")
    @Operation(summary = "添加课程")
    public Result saveCourse(@RequestBody CourseDto courseDto) {
        courseService.save(courseDto);
        log.info("添加课程成功:{}", courseDto);
        return Result.success();
    }

    /**
     * 修改课程信息
     * @param course
     * @return
     */
    @PutMapping("/updateCourse")
    @PreAuthorize("hasAnyAuthority('admin','teacher')")
    @Operation(summary = "修改课程信息")
    public Result updateCourse(@RequestBody Course course) {
        courseService.update(course);
        log.info("修改课程信息成功:{}", course);
        return Result.success();
    }

    /**
     * 删除课程
     * @param id
     * @return
     */
    @DeleteMapping("/del/{id}")
    @PreAuthorize("hasAnyAuthority('admin','teacher')")
    @Operation(summary = "删除课程")
    public Result delCourse(@PathVariable Long id) {
        courseService.delById(id);
        log.info("删除课程成功:{}", id);
        return Result.success();
    }

    /**
     * 根据教师id查询课程信息
     * @return
     */
    @GetMapping("/getByTeaId/{id}")
    @Operation(summary = "根据教师id查询课程信息")
    public Result getCourseByTeaId(@PathVariable Long id){
        List<CourseVo> courseVo = courseService.getCourseByTeaId(id);
        log.info("根据教师id查询课程信息:{}", courseVo.size());
        return Result.success(courseVo);
    }

}
