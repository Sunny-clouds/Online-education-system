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
        // TODO 后续改为在考试类型的活动中查询成绩
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
    public Result getScoreByUserName(@RequestParam String username) {
        // TODO 后续改为在考试类型的活动中查询成绩
        List<ScoreVo> scores = scoreService.getScoreByUserName(username);
        log.info("根据学生姓名查询成绩成功:{}", scores.size());
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
        // TODO 后续改为在考试类型的活动中查询成绩
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
    public Result setScore(ScoreDto scoredto){
        // TODO 后续改为在考试类型的活动中查询成绩
        scoreService.setScore(scoredto);
        log.info("修改成绩成功:{}", scoredto);
        return Result.success();
    }
}
