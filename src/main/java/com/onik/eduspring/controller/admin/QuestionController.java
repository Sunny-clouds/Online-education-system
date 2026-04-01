package com.onik.eduspring.controller.admin;

import com.onik.eduspring.dto.ExamTitleDto;
import com.onik.eduspring.result.Result;
import com.onik.eduspring.service.QuestionService;
import com.onik.eduspring.vo.QuestionVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 接收题目请求
 */
@RestController
@RequestMapping("/api/question")
@Slf4j
@PreAuthorize("hasAnyAuthority('admin','teacher')")
public class QuestionController {

    @Autowired
    private QuestionService questionService;


    /**
     * 新增题目
     * @return
     */
    @Transactional
    @PostMapping("/saveExamTitle")
    public Result saveExamTitle(@RequestBody ExamTitleDto examTitleDto){
        questionService.saveExamTitle(examTitleDto);
        return Result.success();
    }

    /**
     * 修改题目
     * @return
     */
    @Transactional
    @PostMapping("/updateExamTitle")
    public Result updateExamTitle(@RequestBody ExamTitleDto examTitleDto){
        questionService.updateExamTitle(examTitleDto);
        return Result.success();
    }

    /**
     * 获取题库所有题目信息
     * @return
     */
    @GetMapping("/getAllExamTitle")
    public Result getAllExamTitle(){
        List<QuestionVo> examTitles = questionService.getAllExamTitle();
        return Result.success(examTitles);
    }

    /**
     * 根据id删除题库题目
     * @param id
     * @return
     */
    @Transactional
    @GetMapping("/delExamTitle/{id}")
    public Result delExamTitle(@PathVariable Long id){
        questionService.delExamTitle(id);
        return Result.success();
    }
}
