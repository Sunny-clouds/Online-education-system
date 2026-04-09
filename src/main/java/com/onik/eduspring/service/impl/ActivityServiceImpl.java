package com.onik.eduspring.service.impl;

import com.onik.eduspring.dto.ActivityCommentDto;
import com.onik.eduspring.dto.ActivityDto;
import com.onik.eduspring.entity.Activity;
import com.onik.eduspring.entity.ActivityDiscussionRecord;
import com.onik.eduspring.mapper.ActivityMapper;
import com.onik.eduspring.result.Result;
import com.onik.eduspring.service.ActivityService;
import com.onik.eduspring.vo.ActivityCommentVo;
import com.onik.eduspring.vo.ActivityVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    /**
     * 根据课程id查询所有活动信息
     * @param id
     * @return
     */
    @Override
    public List<ActivityVo> getAllById(Long id) {
        return activityMapper.getAllById(id);
    }

    /**
     * 发布活动
     * @param activityDto
     */
    @Override
    public void save(ActivityDto activityDto) {
        Activity activity = new Activity();
        BeanUtils.copyProperties(activityDto, activity);
        activity.setCreateTime(LocalDateTime.now());
        activity.setStatus(1);
        activityMapper.save(activity);
    }

    /**
     * 根据活动id删除活动信息
     * @param id
     */
    @Transactional
    @Override
    public Result delById(Long id) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startTime = activityMapper.getStartTime(id);
        LocalDateTime endTime = activityMapper.getEndTime(id);
        if (now.isAfter(startTime) && now.isBefore(endTime)) {
            return Result.error("活动进行中，不能删除");
        }
        activityMapper.delById(id);
        return Result.success();
    }

    /**
     * 回复活动
     * @param activityCommentDto
     */
    @Transactional
    @Override
    public Result saveComment(ActivityCommentDto activityCommentDto) {
        ActivityDiscussionRecord user = activityMapper.getByActivityId(activityCommentDto);
        if (user ==  null){
            ActivityDiscussionRecord activityDiscussionRecord = new ActivityDiscussionRecord();
            BeanUtils.copyProperties(activityCommentDto, activityDiscussionRecord);
            Double score = activityMapper.getScore(activityCommentDto.getActivityId());
            activityDiscussionRecord.setScore(score);
            activityDiscussionRecord.setSubmitTime(LocalDateTime.now());
            activityMapper.saveComment(activityDiscussionRecord);
            return Result.success();
        }
        return Result.error("已经参加过活动了，不能重复参加哦");
    }

    /**
     * 根据活动id查看活动的评论
     * @param id
     * @return
     */
    @Override
    public List<ActivityCommentVo> getCommentById(Long id) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startTime = activityMapper.getStartTime(id);
        if (now.isBefore(startTime)) {
            // 未开始
            return null;
        }
        return activityMapper.getCommentById(id);
    }

    /**
     * 设置活动的开始时间和结束时间
     * @param activityDto
     */
    @Override
    public void setStartAndEndTime(ActivityDto activityDto) {
        Activity activity = new Activity();
        BeanUtils.copyProperties(activityDto, activity);
        activityMapper.setStartAndEndTime(activity);
    }


}
