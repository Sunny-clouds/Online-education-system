package com.onik.eduspring.controller.user;


import com.onik.eduspring.dto.ScoreDto;
import com.onik.eduspring.entity.PageResult;
import com.onik.eduspring.result.Result;
import com.onik.eduspring.service.ScoreService;
import com.onik.eduspring.vo.ScoreVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 接收成绩请求
 */
@RestController
@Slf4j
@RequestMapping("/api/score")
@Tag(name = "成绩管理")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    /**
     * 分页查询所有学生科目的成绩信息
     * @return
     */
    @PreAuthorize("hasAnyAuthority('admin','teacher')")
    @GetMapping("/getAll")
    @Operation(summary = "分页查询所有学生科目的成绩信息")
    public Result getAll(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        PageResult<ScoreVo> scores = scoreService.getAll(pageNum, pageSize);
        log.info("查询所有学生科目的成绩信息成功:{}", scores.getTotal());
        return Result.success(scores);
    }

    /**
     * 根据学生姓名查询成绩
     * @param username
     * @return
     */
    @GetMapping("/getScoreByUserName")
    @Operation(summary = "根据学生姓名查询成绩")
    public Result getScoreByUserName(@RequestParam String username) {
        List<ScoreVo> scores = scoreService.getScoreByUserName(username);
        log.info("根据学生姓名查询成绩成功:{}", scores.size());
        return Result.success(scores);
    }

    @PreAuthorize("hasAnyAuthority('admin','teacher')")
    @GetMapping("/getScoreByNameTeacher")
    @Operation(summary = "根据学生姓名查询成绩（老师）")
    public Result getScoreByNameTeacher(@RequestParam String username) {
        List<ScoreVo> scores = scoreService.getScoreByNameTeacher(username);
        log.info("根据学生姓名查询成绩成功(老师):{}", scores.size());
        return Result.success(scores);
    }

    /**
     * 根据课程名查询成绩
     * @param title
     * @return
     */
    @PreAuthorize("hasAnyAuthority('admin','teacher')")
    @GetMapping("/getScoreByTitle")
    @Operation(summary = "根据课程名查询成绩")
    public Result getScoreByTitle(@RequestParam String title,@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        PageResult<ScoreVo> scores = scoreService.getScoreByTitle(title, pageNum, pageSize);
        log.info("根据课程名查询成绩成功:{}", scores.getTotal());
        return Result.success(scores);
    }

    /**
     * 修改成绩
     * @param scoredto
     * @return
     */
    @PreAuthorize("hasAnyAuthority('admin','teacher')")
    @PostMapping("/setScore")
    @Operation(summary = "修改成绩")
    public Result setScore(ScoreDto scoredto){
        scoreService.setScore(scoredto);
        log.info("修改成绩成功:{}", scoredto);
        return Result.success();
    }
}
