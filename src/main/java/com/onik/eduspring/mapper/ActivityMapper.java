package com.onik.eduspring.mapper;

import com.onik.eduspring.dto.ActivityCommentDto;
import com.onik.eduspring.entity.Activity;
import com.onik.eduspring.entity.ActivityDiscussionRecord;
import com.onik.eduspring.vo.ActivityCommentVo;
import com.onik.eduspring.vo.ActivityVo;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface ActivityMapper {

    /**
     * 根据课程id查询所有活动信息
     * @param id
     * @return
     */
    List<ActivityVo> getAllById(Long id);

    /**
     * 发布活动
     * @param activity
     */
    void save(Activity activity);

    /**
     * 根据id逻辑删除活动信息
     * @param id
     */
    void delById(Long id);

    /**
     * 回复活动
     * @param activityDiscussionRecord
     */
    void saveComment(ActivityDiscussionRecord activityDiscussionRecord);

    /**
     * 根据活动id查看活动的评论
     * @param id
     * @return
     */
    List<ActivityCommentVo> getCommentById(Long id);

    /**
     * 根据活动id查询活动分数
     * @param activityId
     * @return
     */
    Long getScore(Long activityId);

    /**
     * 根据学生id查询学生是否参与过该活动
     * @param activityCommentDto
     * @return
     */
    ActivityDiscussionRecord getByActivityId(ActivityCommentDto activityCommentDto);

    /**
     * 根据活动id获取结束时间
     * @param id
     * @return
     */
    LocalDateTime getEndTime(Long id);

    /**
     * 设置活动开始时间和结束时间
     * @param activity
     */
    void setStartAndEndTime(Activity activity);

    /**
     * 获取活动开始时间
     * @param activityId
     * @return
     */
    LocalDateTime getStartTime(Long activityId);

    /**
     * 根据活动id获取课程id
     * @param activityId
     * @return
     */
    Long getCourseIdById(Long activityId);
}
