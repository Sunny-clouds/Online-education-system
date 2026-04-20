package com.onik.eduspring.controller.user;


import com.onik.eduspring.dto.StudentCourseDto;
import com.onik.eduspring.entity.PageResult;
import com.onik.eduspring.result.Result;
import com.onik.eduspring.service.StudentCourseService;
import com.onik.eduspring.vo.StudentCourseVo;
import com.onik.eduspring.vo.StudentsCourseVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 接收选课请求
 */
@RestController
@Slf4j
@RequestMapping("/api/studentCourse")
@Tag(name = "选课管理")
public class StudentCourseController {

    @Autowired
    private StudentCourseService studentCourseService;

    /**
     * 获取所有学生选课信息
     * @return
     */
    @PreAuthorize("hasAnyAuthority('admin','teacher')")
    @GetMapping("/getAll")
    @Operation(summary = "获取所有学生选课信息")
    public Result  getAll(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        PageResult<StudentCourseVo> list = studentCourseService.getAll(pageNum, pageSize);
        log.info("获取所有学生选课信息:{}",list.getTotal());
        return Result.success(list);
    }

    /**
     * 根据学生姓名查询选课信息
     * @param nickname
     * @return
     */
    @GetMapping("/getByUserName")
    @Operation(summary = "根据学生姓名查询选课信息")
    public Result getByUserName(@RequestParam String nickname) {
    	List<StudentCourseVo> list = studentCourseService.getByUserName(nickname);
        log.info("根据学生姓名查询选课信息:{}",list.size());
    	return Result.success(list);
    }

    /**
     * 根据选课id删除选课信息
     * @param id
     * @return
     */
    @DeleteMapping("/delByUserName/{id}")
    @Operation(summary = "根据选课id删除选课信息")
    public Result delByUserName(@PathVariable Long id) {
    	studentCourseService.delByUserName(id);
        log.info("根据选课id删除选课信息:{}",id);
    	return Result.success();
    }

    /**
     * 添加选课信息
     * @return
     */
    @PostMapping("/save")
    @Operation(summary = "添加选课信息")
    public Result save(@RequestBody StudentCourseDto studentCourseDto){
        log.info("添加选课信息:{}",studentCourseDto);
        return studentCourseService.save(studentCourseDto);
    }

    /**
     * 根据选课课程id查询所有学生
     * @return
     */
    @GetMapping("/getCourseById")
    @Operation(summary = "根据选课课程id查询所有学生")
    public Result getCourseById(@RequestParam Long id,@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize){
        PageResult<StudentsCourseVo> list = studentCourseService.getCourseById(id,pageNum, pageSize);
        log.info("根据选课课程id查询所有学生:{}",list.getTotal());
        return Result.success(list);
    }
}
