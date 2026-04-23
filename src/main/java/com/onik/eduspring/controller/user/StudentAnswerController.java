package com.onik.eduspring.controller.user;

import com.onik.eduspring.dto.PublishStudentExamDto;
import com.onik.eduspring.result.Result;
import com.onik.eduspring.service.StudentAnswerService;
import com.onik.eduspring.vo.StudentAnswerVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 接收学生答案请求
 */
@RestController
@RequestMapping("/api/studentAnswer")
@Slf4j
@Tag(name = "学生答案管理")
public class StudentAnswerController {

    @Autowired
    private StudentAnswerService studentAnswerService;

    /**
     * @param studentAnswerDtos
     * @return 返回考试分数
     */
    @PostMapping("/saveStudentAnswer")
    @Operation(summary = "保存学生答案信息并返回成绩")
    public Result saveStudentAnswer(@RequestBody PublishStudentExamDto studentAnswerDtos){
        int score = studentAnswerService.saveStudentAnswer(studentAnswerDtos);
        log.info("保存学生答案信息成功:{}", studentAnswerDtos);
        return Result.success(score);
    }

    /**
     * 根据试卷ID获取学生答案信息
     * @return
     */
    @PreAuthorize("hasAnyAuthority('teacher')")
    @GetMapping("/getStudentAnswerByPaperId/{paperId}")
    @Operation(summary = "获取学生答案信息")
    public Result getStudentAnswerByPaperId(@PathVariable Long paperId){
        List<StudentAnswerVo> studentAnswer = studentAnswerService.getStudentAnswerByPaperId(paperId);
    	return Result.success(studentAnswer);
    }
}
