package com.onik.eduspring.mapper;

import com.onik.eduspring.dto.ActivityCommentDto;
import com.onik.eduspring.entity.Activity;
import com.onik.eduspring.entity.ActivityDiscussionRecord;
import com.onik.eduspring.vo.ActivityCommentVo;
import com.onik.eduspring.vo.ActivityVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface ActivityMapper {

    /**
     * 根据课程id查询所有活动信息
     * @param id
     * @return
     */
    @Select("select a.id,a.biz_id, a.title, a.score, a.create_time,a.type,a.status,a.start_time,a.end_time,COUNT(ar.id) AS comment_count ,count(sp.student_id) as exam_sum " +
            "from activity a " +
            "LEFT JOIN activity_discussion_record ar ON a.id = ar.activity_id " +
            "left join student_paper sp on sp.activity_id = a.id " +
            "where a.course_id = #{id} and a.status != 0 " +
            "GROUP BY a.id")
    List<ActivityVo> getAllById(Long id);

    /**
     * 发布活动
     * @param activity
     */
    @Insert("insert into activity(biz_id,course_id,title, score,create_time,type,status,start_time,end_time) values(#{bizId},#{courseId},#{title}, #{score},#{createTime},#{type},#{status},#{startTime},#{endTime})")
    void save(Activity activity);

    /**
     * 根据id逻辑删除活动信息
     * @param id
     */
    @Update("update activity set status = 0 where id = #{id}")
    void delById(Long id);

    /**
     * 回复活动
     * @param activityDiscussionRecord
     */
    @Insert("insert into activity_discussion_record(activity_id, student_id, submit_time,content) values(#{activityId}, #{studentId}, #{submitTime},#{content})")
    void saveComment(ActivityDiscussionRecord activityDiscussionRecord);

    /**
     * 根据活动id查看活动的评论
     * @param id
     * @return
     */
    @Select("select a.id, u.nickname,u.avatar ,a.submit_time,a.content from activity_discussion_record a left JOIN `user` u ON u.id = a.student_id where a.activity_id = #{id}")
    List<ActivityCommentVo> getCommentById(Long id);

    /**
     * 根据活动id查询活动分数
     * @param activityId
     * @return
     */
    @Select("select score from activity where id = #{activityId}")
    Long getScore(Long activityId);

    /**
     * 根据学生id查询学生是否参与过该活动
     * @param activityCommentDto
     * @return
     */
    @Select("select * from activity_discussion_record where student_id = #{studentId} and activity_id = #{activityId}")
    ActivityDiscussionRecord getByActivityId(ActivityCommentDto activityCommentDto);

    /**
     * 根据活动id获取结束时间
     * @param id
     * @return
     */
    @Select("select end_time from activity where id = #{id}")
    LocalDateTime getEndTime(Long id);

    /**
     * 设置活动开始时间和结束时间
     * @param activity
     */
    @Update("update activity set start_time = #{startTime},end_time = #{endTime} where id = #{id} and status != 0")
    void setStartAndEndTime(Activity activity);

    /**
     * 获取活动开始时间
     * @param activityId
     * @return
     */
    @Select("select start_time from activity where id = #{activityId}")
    LocalDateTime getStartTime(Long activityId);

    /**
     * 根据活动id获取课程id
     * @param activityId
     * @return
     */
    @Select("select course_id from activity where id = #{activityId}")
    Long getCourseIdById(Long activityId);
}
