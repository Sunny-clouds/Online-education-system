package com.onik.eduspring.service;

import com.onik.eduspring.dto.ActivityCommentDto;
import com.onik.eduspring.dto.ActivityDto;
import com.onik.eduspring.result.Result;
import com.onik.eduspring.vo.ActivityCommentVo;
import com.onik.eduspring.vo.ActivityVo;

import java.util.List;

public interface ActivityService {
    /**
     * 根据课程id获取课程下的所有活动信息
     * @param id
     * @return
     */
    List<ActivityVo> getAllById(Long id);

    /**
     * 发布活动
     * @param activityDto
     */
    void save(ActivityDto activityDto);

    /**
     * 删除活动
     * @param id
     */
    Result delById(Long id);

    /**
     * 回复活动
     * @param activityCommentDto
     */
    Result saveComment(ActivityCommentDto activityCommentDto);

    /**
     * 根据活动id获取活动下的所有评论信息
     * @param id
     * @return
     */
    List<ActivityCommentVo> getCommentById(Long id);

    /**
     * 设置活动开始和结束的时间
     * @param activityDto
     */
    void setStartAndEndTime(ActivityDto activityDto);
}
