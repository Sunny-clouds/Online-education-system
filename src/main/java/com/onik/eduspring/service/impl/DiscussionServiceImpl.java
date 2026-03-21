package com.onik.eduspring.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.onik.eduspring.dto.DiscussionCommentDto;
import com.onik.eduspring.entity.Discussion;
import com.onik.eduspring.entity.DiscussionComment;
import com.onik.eduspring.entity.PageResult;
import com.onik.eduspring.mapper.DiscussionMapper;
import com.onik.eduspring.service.DiscussionService;
import com.onik.eduspring.vo.DiscussionCommentVo;
import com.onik.eduspring.vo.DiscussionVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DiscussionServiceImpl implements DiscussionService {

    @Autowired
    private DiscussionMapper discussionMapper;

    //获取所有帖子
    public PageResult<DiscussionVo> getAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<DiscussionVo> discussions = discussionMapper.getAll();
        PageInfo<DiscussionVo> p = new PageInfo<>(discussions);
        return new PageResult<DiscussionVo>(p.getTotal(),p.getList());
    }

    //根据id删除帖子
    public void delById(Long id) {
        discussionMapper.delById(id);
    }

    //添加帖子
    public void save(Discussion discussion) {
        discussion.setLikeCount(0L);
        discussion.setCommentCount(0L);
        discussion.setIsTop(0L);
        discussion.setStatus(1L);
        discussion.setCreateTime(LocalDateTime.now());
        discussionMapper.save(discussion);
    }

    //根据标题查询帖子
    @Override
    public PageResult<DiscussionVo> getByTitle(String title, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<DiscussionVo> discussions = discussionMapper.getByTitle(title);
        PageInfo<DiscussionVo> p = new PageInfo<>(discussions);
        return new PageResult<>(p.getTotal(),p.getList());
    }

    //根据课程查询帖子
    @Override
    public PageResult<DiscussionVo> getByCourseName(String courseName, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<DiscussionVo> discussions = discussionMapper.getByCourseName(courseName);
        PageInfo<DiscussionVo> p = new PageInfo<>(discussions);
        return new PageResult<>(p.getTotal(),p.getList());
    }

    //查询帖子下的评论
    @Override
    public List<DiscussionCommentVo>  getByPostId(Long id) {
        List<DiscussionCommentVo> discussionCommentVo = discussionMapper.getByPostId(id);
        return buildTree(discussionCommentVo);
    }

    /**
     * 将平铺评论列表组装成树状结构
     */
    private List<DiscussionCommentVo> buildTree(List<DiscussionCommentVo> discussionCommentVo) {
        // 按 parentId 分组
        Map<Long, List<DiscussionCommentVo>> grouped = discussionCommentVo.stream()
                .collect(Collectors.groupingBy(DiscussionCommentVo::getParentId));
        // 一级评论
        List<DiscussionCommentVo> rootComments = grouped.getOrDefault(0L, new ArrayList<>());
        // 递归挂子评论
        rootComments.forEach(comment -> attachChildren(comment, grouped));
        return rootComments;
    }

    /**
     * 递归挂子评论到 parent 的 children 字段
     */
    private void attachChildren(DiscussionCommentVo parent, Map<Long, List<DiscussionCommentVo>> grouped) {
        List<DiscussionCommentVo> children = grouped.get(parent.getId());
        if (children != null) {
            parent.setChildren(children);
            children.forEach(child -> attachChildren(child, grouped));
        }
    }


    //回复帖子
    @Override
    public void saveComment(DiscussionCommentDto discussionCommentDto) {
        DiscussionComment discussionComment = new DiscussionComment();
        BeanUtils.copyProperties(discussionCommentDto, discussionComment);
        discussionComment.setLikeCount(0L);
        discussionComment.setStatus(1);
        discussionComment.setCreateTime(LocalDateTime.now());
        discussionMapper.saveComment(discussionComment);
        //更新帖子评论数
        discussionMapper.updateCount(discussionCommentDto.getPostId());
    }

    @Override
    public void updatePostLike(Long id) {
        discussionMapper.updatePostLike(id);
    }

    @Override
    public void updateCommentLike(Long id) {
        discussionMapper.updateCommentLike(id);
    }

    @Override
    public void delComment(Long id) {
        discussionMapper.delComment(id);
    }


}
