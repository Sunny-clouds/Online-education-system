package com.onik.eduspring.controller.user;


import com.onik.eduspring.dto.ScoreDto;
import com.onik.eduspring.entity.PageResult;
import com.onik.eduspring.result.Result;
import com.onik.eduspring.service.ScoreService;
import com.onik.eduspring.vo.ScoreVo;
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
public class ScoreController {


    @Autowired
    private ScoreService scoreService;

    /**
     * 分页查询所有学生科目的成绩信息
     * @return
     */
    @PreAuthorize("hasAnyAuthority('admin','teacher')")
    @GetMapping("/getAll")
    public Result getAll(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize) {
        PageResult<ScoreVo> scores = scoreService.getAll(pageNum, pageSize);
        return Result.success(scores);
    }

    /**
     * 根据学生姓名查询成绩
     * @param username
     * @return
     */
    @GetMapping("/getScoreByUserName")
    public Result getScoreByUserName(@RequestParam String username) {
        List<ScoreVo> scores = scoreService.getScoreByUserName(username);
        return Result.success(scores);
    }

    /**
     * 根据课程名查询成绩
     * @param title
     * @return
     */
    @PreAuthorize("hasAnyAuthority('admin','teacher')")
    @GetMapping("/getScoreByTitle")
    public Result getScoreByTitle(@RequestParam String title,@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize) {
        PageResult<ScoreVo> scores = scoreService.getScoreByTitle(title, pageNum, pageSize);
        return Result.success(scores);
    }

    /**
     * 修改成绩
     * @param scoredto
     * @return
     */
    @PreAuthorize("hasAnyAuthority('admin','teacher')")
    @PostMapping("/setScore")
    public Result setScore(ScoreDto scoredto){
        scoreService.setScore(scoredto);
        return Result.success();
    }
}
