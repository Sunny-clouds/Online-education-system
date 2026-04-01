package com.onik.eduspring.mapper;

import com.onik.eduspring.entity.Discussion;
import com.onik.eduspring.entity.DiscussionComment;
import com.onik.eduspring.vo.DiscussionCommentVo;
import com.onik.eduspring.vo.DiscussionVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface DiscussionMapper {

    /**
     * 获取所有讨论信息
     * @return
     */

    List<DiscussionVo> getAll();

    /**
     * 根据id删除讨论信息
     * @param id
     */
    @Delete("delete from discussion_post where id=#{id}")
    void delById(Long id);

    /**
     * 保存讨论信息
     * @param discussion
     */
    void save(Discussion discussion);

    /**
     * 根据标题查询讨论信息
     * @param title
     * @return
     */
    List<DiscussionVo> getByTitle(String title);

    /**
     * 根据课程名称查询讨论信息
     * @param courseName
     * @return
     */
    List<DiscussionVo> getByCourseName(String courseName);

    /**
     * 根据帖子id查询帖子下的评论
     * @param id
     * @return
     */
    List<DiscussionCommentVo> getByPostId(Long id);

    /**
     * 回复帖子
     * @param discussionComment
     */
    void saveComment(DiscussionComment discussionComment);

    /**
     * 更新帖子点赞数
     * @param id
     */
    @Update("update discussion_post set like_count = like_count + 1 where id = #{id}")
    void updatePostLike(Long id);

    /**
     * 更新评论点赞数
     * @param id
     */
    @Update("update discussion_comment set like_count = like_count + 1 where id = #{id}")
    void updateCommentLike(Long id);

    /**
     * 根据id删除评论
     * @param id
     */
    @Delete("delete from discussion_comment where id= #{id}")
    void delComment(Long id);


}
