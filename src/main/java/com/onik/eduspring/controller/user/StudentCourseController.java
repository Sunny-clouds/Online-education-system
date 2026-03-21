package com.onik.eduspring.controller.user;


import com.onik.eduspring.dto.StudentCourseDto;
import com.onik.eduspring.dto.StudentCourseStatusDto;
import com.onik.eduspring.entity.PageResult;
import com.onik.eduspring.result.Result;
import com.onik.eduspring.service.StudentCourseService;
import com.onik.eduspring.vo.StudentCourseVo;
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
public class StudentCourseController {

    @Autowired
    private StudentCourseService studentCourseService;

    /**
     * 获取所有学生选课信息
     * @return
     */
    @PreAuthorize("hasAnyAuthority('admin','teacher')")
    @GetMapping("/getAll")
    public Result  getAll(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize) {
        PageResult<StudentCourseVo> list = studentCourseService.getAll(pageNum, pageSize);
        return Result.success(list);
    }

    /**
     * 根据学生姓名查询选课信息
     * @param nickname
     * @return
     */
    @GetMapping("/getByUserName")
    public Result getByUserName(@RequestParam String nickname) {
    	List<StudentCourseVo> list = studentCourseService.getByUserName(nickname);
    	return Result.success(list);
    }

    /**
     * 根据选课id删除选课信息
     * @param id
     * @return
     */
    @DeleteMapping("/delByUserName/{id}")
    public Result delByUserName(@PathVariable Long id) {
    	studentCourseService.delByUserName(id);
    	return Result.success();
    }

    /**
     * 添加选课信息
     * @return
     */
    @PostMapping("/save")
    public Result save(@RequestBody StudentCourseDto studentCourseDto){
        studentCourseService.save(studentCourseDto);
        return Result.success();
    }

    /**
     * 修改选课进度
     * @param statusDto
     * @return
     */
    @PutMapping("/update")
    public Result update(@RequestBody StudentCourseStatusDto statusDto){
        studentCourseService.update(statusDto);
        return Result.success();
    }
}
