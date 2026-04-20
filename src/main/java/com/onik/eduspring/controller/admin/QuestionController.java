package com.onik.eduspring.controller.admin;

import com.onik.eduspring.dto.ExamTitleDto;
import com.onik.eduspring.result.Result;
import com.onik.eduspring.service.QuestionService;
import com.onik.eduspring.vo.QuestionVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "题库管理")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    /**
     * 新增题目
     * @return
     */
    @Transactional
    @PostMapping("/saveExamTitle")
    @Operation(summary = "新增题目")
    public Result saveExamTitle(@RequestBody ExamTitleDto examTitleDto){
        questionService.saveExamTitle(examTitleDto);
        log.info("新增题目成功:{}", examTitleDto);
        return Result.success();
    }

    /**
     * 修改题目
     * @return
     */
    @Transactional
    @PostMapping("/updateExamTitle")
    @Operation(summary = "修改题目")
    public Result updateExamTitle(@RequestBody ExamTitleDto examTitleDto){
        questionService.updateExamTitle(examTitleDto);
        log.info("修改题目成功:{}", examTitleDto);
        return Result.success();
    }

    /**
     * 获取题库所有题目信息
     * @return
     */
    @GetMapping("/getAllExamTitle")
    @Operation(summary = "获取题库所有题目信息")
    public Result getAllExamTitle(){
        List<QuestionVo> examTitles = questionService.getAllExamTitle();
        log.info("获取题库所有题目信息:{}", examTitles.size());
        return Result.success(examTitles);
    }

    /**
     * 根据id删除题库题目
     * @param id
     * @return
     */
    @Transactional
    @GetMapping("/delExamTitle/{id}")
    @Operation(summary = "根据id删除题库题目")
    public Result delExamTitle(@PathVariable Long id){
        questionService.delExamTitle(id);
        log.info("删除题库题目成功:{}", id);
        return Result.success();
    }
}
