package com.onik.eduspring.service;

import com.onik.eduspring.dto.DiscussionCommentDto;
import com.onik.eduspring.dto.DiscussionDto;
import com.onik.eduspring.entity.PageResult;
import com.onik.eduspring.vo.DiscussionCommentVo;
import com.onik.eduspring.vo.DiscussionVo;

import java.util.List;

public interface DiscussionService {

    //查询所有帖子
    PageResult<DiscussionVo> getAll(Integer pageNum, Integer pageSize);

    //根据id删除帖子
    void delById(Long id);

    //添加帖子
    void save(DiscussionDto discussionDto);

    //根据标题查询帖子
    PageResult<DiscussionVo> getByTitle(String title, Integer pageNum, Integer pageSize);

    //根据课程查询帖子
    PageResult<DiscussionVo> getByCourseName(String courseName, Integer pageNum, Integer pageSize);

    //查询帖子下的评论
    List<DiscussionCommentVo> getByPostId(Long id);

    //回复帖子
    void saveComment(DiscussionCommentDto discussionCommentDto);

    //更新帖子点赞数
    void updatePostLike(Long id);

    //更新评论点赞数
    void updateCommentLike(Long id);

    //根据id删除帖子下的评论
    void delComment(Long id);

}
