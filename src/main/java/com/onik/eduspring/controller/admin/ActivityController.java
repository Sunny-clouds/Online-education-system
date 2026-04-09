package com.onik.eduspring.controller.admin;

import com.onik.eduspring.dto.ActivityCommentDto;
import com.onik.eduspring.dto.ActivityDto;
import com.onik.eduspring.result.Result;
import com.onik.eduspring.service.ActivityService;
import com.onik.eduspring.vo.ActivityCommentVo;
import com.onik.eduspring.vo.ActivityVo;
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
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    /**
     * 获取课程下的所有活动信息
     * @return
     */
    @GetMapping("/getAllById/{id}")
    public Result getAllById(@PathVariable Long id){
        List<ActivityVo> activities = activityService.getAllById(id);
        return Result.success(activities);
    }

    /**
     * 发布活动信息
     * @return
     */
    @PreAuthorize("hasAnyAuthority('admin','teacher')")
    @PostMapping("/save")
    public Result save(@RequestBody ActivityDto activityDto){
        activityService.save(activityDto);
        return Result.success();
    }

    /**
     * 删除活动信息
     * @return
     */
    @PreAuthorize("hasAnyAuthority('admin','teacher')")
    @DeleteMapping("/delById/{id}")
    public Result delById(@PathVariable Long id){
        return activityService.delById(id);
    }

    /**
     * 回复活动评论
     * @return
     */
    @PostMapping("/saveComment")
    public Result saveComment(@RequestBody ActivityCommentDto activityCommentDto){
        return activityService.saveComment(activityCommentDto);
    }

    /**
     * 根据活动id查看活动的评论
     * @param id
     * @return
     */
    @GetMapping("/getCommentById/{id}")
    public Result getCommentById(@PathVariable Long id){
        List<ActivityCommentVo> activity = activityService.getCommentById(id);
        return Result.success(activity);
    }

    /**
     * 修改活动开始和结束时间
     * @return
     */
    @PreAuthorize("hasAnyAuthority('admin','teacher')")
    @PutMapping("/setStartAndEndTime")
    public Result setStartAndEndTime(@RequestBody ActivityDto activityDto){
        activityService.setStartAndEndTime(activityDto);
        return Result.success();
    }
}
