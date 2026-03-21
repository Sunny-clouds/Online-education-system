package com.onik.eduspring.controller.user;

import com.onik.eduspring.dto.DiscussionCommentDto;
import com.onik.eduspring.entity.Discussion;
import com.onik.eduspring.entity.PageResult;
import com.onik.eduspring.result.Result;
import com.onik.eduspring.service.DiscussionService;
import com.onik.eduspring.vo.DiscussionCommentVo;
import com.onik.eduspring.vo.DiscussionVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 接收讨论请求
 */
@RestController
@Slf4j
@RequestMapping("/api/discussion")
public class DiscussionController {

    @Autowired
    private DiscussionService discussionService;

    /**
     * 分页查询所有帖子
     * @return
     */
    @GetMapping("/getAll")
    public Result getAll(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize) {
        PageResult<DiscussionVo> discussions = discussionService.getAll(pageNum, pageSize);
        return Result.success(discussions);
    }

    /**
     * 根据id删除帖子
     * @param id
     * @return
     */
    @PreAuthorize("hasAnyAuthority('admin','teacher')")
    @DeleteMapping("/del/{id}")
    public Result delById(@PathVariable Long id){
        discussionService.delById(id);
        return Result.success();
    }

    /**
     * 添加帖子
     * @param discussion
     * @return
     */
    @PostMapping("/save")
    public Result save(@RequestBody Discussion discussion){
        discussionService.save(discussion);
        return Result.success();
    }

    /**
     * 根据标题查询帖子
     * @param title
     * @return
     */
    @GetMapping("/getByTitle")
    public Result getByTitle(@RequestParam String title,@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize) {
        PageResult<DiscussionVo> discussionVo = discussionService.getByTitle(title ,pageNum, pageSize);
        return Result.success(discussionVo);
    }

    /**
     * 根据课程查询帖子
     * @param courseName
     * @return
     */
    @GetMapping("/getByCourseName")
    public Result getByCourseName(@RequestParam String courseName,@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize) {
        PageResult<DiscussionVo> discussionVo = discussionService.getByCourseName(courseName ,pageNum, pageSize);
        return Result.success(discussionVo);
    }

    /**
     * 查询帖子下的评论
     * @param id
     * @return
     */
    @GetMapping("/comments/{id}")
    public Result getComments(@PathVariable Long id) {
        List<DiscussionCommentVo> list = discussionService.getByPostId(id);
        return Result.success(list);
    }

    /**
     * 回复帖子
     * @return
     */
    @PostMapping("/saveComment")
    public Result saveComment(@RequestBody DiscussionCommentDto discussionCommentDto){
        discussionService.saveComment(discussionCommentDto);
        return Result.success();
    }

    /**
     * 更新帖子点赞数
     * @return
     */
    @GetMapping("/postLike/{id}")
    public Result postLike(@PathVariable Long id){
        discussionService.updatePostLike(id);
        return Result.success();
    }

    /**
     * 更新评论点赞数
     * @return
     */
    @GetMapping("/commentLike/{id}")
    public Result commentLike(@PathVariable Long id){
        discussionService.updateCommentLike(id);
        return Result.success();
    }

    /**
     * 根据id删除评论
     * @param id
     * @return
     */
    @DeleteMapping("/delComment/{id}")
    public Result delComment(@PathVariable Long id){
        discussionService.delComment(id);
        return Result.success();
    }
}
