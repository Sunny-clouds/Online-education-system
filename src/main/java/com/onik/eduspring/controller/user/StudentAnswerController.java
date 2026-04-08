package com.onik.eduspring.controller.user;

import com.onik.eduspring.dto.PublishStudentExamDto;
import com.onik.eduspring.result.Result;
import com.onik.eduspring.service.StudentAnswerService;
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
public class StudentAnswerController {

    @Autowired
    private StudentAnswerService studentAnswerService;

    /**
     * 保存学生答案信息
     * @return
     */
    @PostMapping("/saveStudentAnswer")
    public Result saveStudentAnswer(@RequestBody PublishStudentExamDto studentAnswerDtos){
        studentAnswerService.saveStudentAnswer(studentAnswerDtos);
        return Result.success();
    }
}
