package com.onik.eduspring.controller.user;

import com.onik.eduspring.dto.ExamDto;
import com.onik.eduspring.result.Result;
import com.onik.eduspring.service.ExamService;
import com.onik.eduspring.vo.ExamVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 接收考试请求
 */

@RestController
@RequestMapping("/api/exam")
@Slf4j
public class ExamController {

    @Autowired
    private ExamService examService;

    /**
     * 根据活动id获取考试信息
     * @param activityId
     * @return
     */
    @GetMapping("/getExamByActivityId/{activityId}")
    public Result getExamByActivityId(@PathVariable Long activityId){
        ExamVo examVo = examService.getExamByActivityId(activityId);
        return Result.success(examVo);
    }

    /**
     * 新增考试信息
     * @param examDto
     * @return
     */
    @PreAuthorize("hasAnyAuthority('admin','teacher')")
    @PostMapping("/saveExam")
    public Result saveExam(@RequestBody ExamDto examDto){
        examService.saveExam(examDto);
        return Result.success();
    }

    /**
     * 修改考试信息
     * @param examDto
     * @return
     */
    @PreAuthorize("hasAnyAuthority('admin','teacher')")
    @PostMapping("/updateExam")
    public Result updateExam(@RequestBody ExamDto examDto){
        return examService.updateExam(examDto);
    }


}
