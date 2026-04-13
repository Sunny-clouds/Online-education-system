package com.onik.eduspring.controller.user;

import com.onik.eduspring.dto.ExamDto;
import com.onik.eduspring.dto.PublishExamDto;
import com.onik.eduspring.entity.StudentPaper;
import com.onik.eduspring.result.Result;
import com.onik.eduspring.service.ExamActivityService;
import com.onik.eduspring.service.ExamService;
import com.onik.eduspring.service.StudentPaperService;
import com.onik.eduspring.vo.ExamVo;
import com.onik.eduspring.vo.StudentPaperVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 接收考试请求
 */

@RestController
@RequestMapping("/api/exam")
@Slf4j
public class ExamController {

    @Autowired
    private ExamService examService;
    @Autowired
    private ExamActivityService examActivityService;
    @Autowired
    private StudentPaperService studentPaperService;

    /**
     * 根据业务id获取考试信息
     * @param bizId
     * @return
     */
    @GetMapping("/getExamByBizId/{bizId}")
    public Result getExamByBizId(@PathVariable Long bizId){
        ExamVo examVo = examService.getExamByBizId(bizId);
        log.info("获取考试信息成功:{}", examVo);
        return Result.success(examVo);
    }

    /**
     * 新增考试信息
     * @param examDto
     * @return
     */
    @PreAuthorize("hasAnyAuthority('admin','teacher')")
    @PostMapping("/saveExam")
    public Result saveExam(@RequestBody PublishExamDto examDto ){
        examActivityService.publishExamActivity(examDto);
        log.info("新增考试信息成功:{}", examDto);
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
        log.info("修改考试信息成功:{}", examDto);
        return examService.updateExam(examDto);
    }

    /**
     * 根据试卷id和学生id查询考试成绩
     * @param studentId
     * @param paperId
     * @return
     */
    @GetMapping("/getScoreByStudentIdAndPaperId/{studentId}/{paperId}")
    public Result getScoreByStudentIdAndPaperId(@PathVariable Long studentId,@PathVariable Long paperId){
        StudentPaper score = studentPaperService.getScoreByStudentIdAndPaperId(studentId, paperId);
        log.info("查询考试成绩成功:{}", score);
        return Result.success(score);
    }

    /**
     * 打回学生的考试信息
     * @param id
     * @return
     */
    @PreAuthorize("hasAnyAuthority('admin','teacher')")
    @DeleteMapping("/delById/{id}")
    public Result delById(@PathVariable Long id){
        studentPaperService.delById(id);
        log.info("打回学生考试信息成功:{}", id);
        return Result.success();
    }

    /**
     * 查询所有学生的考试信息
     * @param activityId
     * @return
     */
    @PreAuthorize("hasAnyAuthority('admin','teacher')")
    @GetMapping("/getAllStudentPaper/{activityId}")
    public Result getAllStudentPaper(@PathVariable Long activityId){
        List<StudentPaperVo> studentPapers = studentPaperService.getAllStudentPaper(activityId);
        log.info("查询所有学生的考试信息成功:{}", studentPapers.size());
        return Result.success(studentPapers);
    }
}
