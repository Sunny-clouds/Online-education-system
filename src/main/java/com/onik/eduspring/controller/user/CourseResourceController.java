package com.onik.eduspring.controller.user;

import com.onik.eduspring.dto.CourseResourceDto;
import com.onik.eduspring.result.Result;
import com.onik.eduspring.service.CourseResourceService;
import com.onik.eduspring.vo.CourseResourceVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 课程资源控制器
 */
@RestController
@Slf4j
@RequestMapping("/api/courseResource")
public class CourseResourceController {

    @Autowired
    private CourseResourceService courseResourceService;

    /**
     * 根据课程id获取课程资源
     * @return
     */
    @GetMapping("/getCourseResourceById/{id}")
    public Result getCourseResourceById(@PathVariable Long id) {
        List<CourseResourceVo> courseResourceVo = courseResourceService.getCourseResourceById(id);
        if (courseResourceVo != null){
            log.info("获取课程资源成功:{}", courseResourceVo.size());
            return Result.success(courseResourceVo);
        }
        log.info("获取课程资源失败:{}", id);
        return Result.success();
    }

    /**
     * 根据id删除课程资源
     * @param id
     * @return
     */
    @PreAuthorize("hasAnyAuthority('admin','teacher')")
    @DeleteMapping("/del/{id}")
    public Result delCourseResourceById(@PathVariable Long id) {
        courseResourceService.delCourseResourceById(id);
        log.info("删除课程资源成功:{}", id);
        return Result.success();
    }

    /**
     * 上传课程资源
     * @return
     */
    @PreAuthorize("hasAnyAuthority('admin','teacher')")
    @PostMapping("/save")
    public Result save(@RequestBody CourseResourceDto courseResourceDto){
        courseResourceService.save(courseResourceDto);
        log.info("上传课程资源成功:{}", courseResourceDto);
        return Result.success();
    }
}
