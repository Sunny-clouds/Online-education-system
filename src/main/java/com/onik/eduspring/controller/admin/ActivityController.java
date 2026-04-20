package com.onik.eduspring.controller.admin;

import com.onik.eduspring.dto.ActivityCommentDto;
import com.onik.eduspring.dto.ActivityDto;
import com.onik.eduspring.result.Result;
import com.onik.eduspring.service.ActivityService;
import com.onik.eduspring.vo.ActivityCommentVo;
import com.onik.eduspring.vo.ActivityVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 接收活动请求
 */
@RestController
@RequestMapping("/api/activity")
@Slf4j
@Tag(name = "活动管理模块")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    /**
     * 获取课程下的所有活动信息
     * @return
     */
    @GetMapping("/getAllById/{id}")
    @Operation(summary = "获取课程下的所有活动信息")
    public Result getAllById(@PathVariable Long id){
        List<ActivityVo> activities = activityService.getAllById(id);
        log.info("获取课程下的所有活动:{}", activities.size());
        return Result.success(activities);
    }

    /**
     * 发布活动信息
     * @return
     */
    @PreAuthorize("hasAnyAuthority('admin','teacher')")
    @PostMapping("/save")
    @Operation(summary = "发布活动信息")
    public Result save(@RequestBody ActivityDto activityDto){
        activityService.save(activityDto);
        log.info("发布活动信息:{}", activityDto);
        return Result.success();
    }

    /**
     * 删除活动信息
     * @return
     */
    @PreAuthorize("hasAnyAuthority('admin','teacher')")
    @DeleteMapping("/delById/{id}")
    @Operation(summary = "删除活动信息")
    public Result delById(@PathVariable Long id){
        log.info("删除活动信息:{}", id);
        return activityService.delById(id);
    }

    /**
     * 回复活动评论
     * @return
     */
    @Operation(summary = "回复活动评论")
    @PostMapping("/saveComment")
    public Result saveComment(@RequestBody ActivityCommentDto activityCommentDto){
        log.info("回复活动评论:{}", activityCommentDto);
        return activityService.saveComment(activityCommentDto);
    }

    /**
     * 根据活动id查看活动的评论
     * @param id
     * @return
     */
    @Operation(summary = "根据活动id查看活动的评论")
    @GetMapping("/getCommentById/{id}")
    public Result getCommentById(@PathVariable Long id){
        List<ActivityCommentVo> activity = activityService.getCommentById(id);
        log.info("根据活动id查看活动的评论:{}", activity.size());
        return Result.success(activity);
    }

    /**
     * 修改活动开始和结束时间
     * @return
     */
    @Operation(summary = "修改活动开始和结束时间")
    @PreAuthorize("hasAnyAuthority('admin','teacher')")
    @PutMapping("/setStartAndEndTime")
    public Result setStartAndEndTime(@RequestBody ActivityDto activityDto){
        activityService.setStartAndEndTime(activityDto);
        log.info("修改活动开始和结束时间:{}", activityDto);
        return Result.success();
    }
}
