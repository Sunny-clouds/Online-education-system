package com.onik.eduspring.service.impl;

import com.onik.eduspring.dto.ExamTitleDto;
import com.onik.eduspring.entity.Question;
import com.onik.eduspring.mapper.QuestionMapper;
import com.onik.eduspring.service.QuestionService;
import com.onik.eduspring.util.BaseContext;
import com.onik.eduspring.vo.QuestionVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    /**
     * 新增题目
     * @return
     */
    @Override
    public void saveExamTitle(ExamTitleDto examTitleDto) {
        Long userId = BaseContext.getUserId();
        Question question = new Question();
        BeanUtils.copyProperties(examTitleDto,question);
        question.setCreateUser(userId);
        question.setCreateTime(LocalDateTime.now());
        questionMapper.saveExamTitle(question);
    }

    /**
     * 修改题目
     * @param examTitleDto
     */
    @Override
    public void updateExamTitle(ExamTitleDto examTitleDto) {
        Question question = new Question();
        BeanUtils.copyProperties(examTitleDto,question);
        question.setUpdateTime(LocalDateTime.now());
        questionMapper.updateExamTitle(question);
    }

    /**
     * 获取题库所有题目信息
     * @return
     */
    @Override
    public List<QuestionVo> getAllExamTitle() {
        return questionMapper.getAllExamTitle();
    }

    /**
     * 根据id删除题目
     * @param id
     */
    @Override
    public void delExamTitle(Long id) {
        questionMapper.delExamTitle(id);
    }
}
