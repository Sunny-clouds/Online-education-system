package com.onik.eduspring.controller.user;

import com.onik.eduspring.dto.PublishStudentExamDto;
import com.onik.eduspring.result.Result;
import com.onik.eduspring.service.StudentAnswerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
