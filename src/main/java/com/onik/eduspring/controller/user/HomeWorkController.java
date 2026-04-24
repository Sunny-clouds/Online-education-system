package com.onik.eduspring.controller.user;


import com.onik.eduspring.dto.HomeWorkDto;
import com.onik.eduspring.result.Result;
import com.onik.eduspring.service.HomeWorkService;
import com.onik.eduspring.vo.HomeWorkVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/homeWork")
@Slf4j
@Tag(name = "作业管理模块")
public class HomeWorkController {

    @Autowired
    private HomeWorkService homeWorkService;

    /**
     * 获取所有学生作业
     * @return
     */
    @PostMapping("/getAll")
    @Operation(summary = "获取所有作业")
    @PreAuthorize("hasAnyAuthority('admin','teacher')")
    public Result getAll(@RequestBody HomeWorkDto homeWorkDto){
        List<HomeWorkVo> homeWork = homeWorkService.getAll(homeWorkDto);
        return Result.success(homeWork);
    }

    /**
     * 新增学生作业
     * @return
     */
    @PostMapping("/save")
    @Operation(summary = "新增作业")
    @PreAuthorize("hasAnyAuthority('student')")
    public Result save(@RequestBody HomeWorkDto homeWorkDto){
        homeWorkService.save(homeWorkDto);
        return Result.success();
    }

    /**
     * 根据学生id和课程id查询作业
     * @param homeWorkDto
     * @return
     */
    @Operation(summary = "根据学生id和课程id查询作业")
    @PostMapping("/getByStudentIdAndCourseId")
    public Result getByStudentIdAndCourseId(@RequestBody HomeWorkDto homeWorkDto){
        HomeWorkVo homeWork = homeWorkService.getByStudentIdAndCourseId(homeWorkDto);
        return Result.success(homeWork);
    }
}
