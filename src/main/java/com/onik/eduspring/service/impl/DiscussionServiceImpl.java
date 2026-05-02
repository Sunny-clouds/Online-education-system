package com.onik.eduspring.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.onik.eduspring.dto.DiscussionCommentDto;
import com.onik.eduspring.dto.DiscussionDto;
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
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public void delById(Long id) {
        discussionMapper.delById(id);
    }

    //添加帖子
    @Transactional
    public void save(DiscussionDto discussionDto) {
        Discussion discussion = new Discussion();
        BeanUtils.copyProperties(discussionDto, discussion);
        discussion.setLikeCount(0L);
        discussion.setIsTop(0L);
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
        // 根据评论 id 建立映射
        Map<Long, DiscussionCommentVo> commentMap = discussionCommentVo.stream()
                .collect(Collectors.toMap(
                        DiscussionCommentVo::getId,
                        comment -> comment,
                        (oldValue, newValue) -> oldValue));
        List<DiscussionCommentVo> rootComments = new ArrayList<>();
        for (DiscussionCommentVo comment : discussionCommentVo) {
            Long parentId = comment.getParentId();
            // parentId 为 null 或 0，都是一级评论
            if (parentId == null || parentId == 0L) {
                rootComments.add(comment);
                continue;
            }
            // 找父评论
            DiscussionCommentVo parentComment = commentMap.get(parentId);
            if (parentComment != null) {
                // 防止父评论 children 是 null
                if (parentComment.getChildren() == null) {
                    parentComment.setChildren(new ArrayList<>());
                }
                // 把当前评论添加到父评论的 children 里
                parentComment.getChildren().add(comment);
            } else {
                // 父评论不存在时，不丢数据，直接当成一级评论返回
                rootComments.add(comment);
            }
        }
        return rootComments;
    }

    /**
     * 回复帖子
     * @param discussionCommentDto
     */
    @Override
    @Transactional
    public void saveComment(DiscussionCommentDto discussionCommentDto) {
        DiscussionComment discussionComment = new DiscussionComment();
        BeanUtils.copyProperties(discussionCommentDto, discussionComment);
        discussionComment.setLikeCount(0L);
        discussionComment.setCreateTime(LocalDateTime.now());
        discussionMapper.saveComment(discussionComment);
    }

    /**
     * 更新帖子点赞数
     * @param id
     */
    @Transactional
    @Override
    public void updatePostLike(Long id) {
        discussionMapper.updatePostLike(id);
    }

    /**
     * 更新评论点赞数
     * @param id
     */
    @Override
    public void updateCommentLike(Long id) {
        discussionMapper.updateCommentLike(id);
    }

    /**
     * 根据id删除评论
     * @param id
     */
    @Override
    @Transactional
    public void delComment(Long id) {
        discussionMapper.delComment(id);
    }


}
