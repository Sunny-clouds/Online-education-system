package com.onik.eduspring.mapper;

import com.onik.eduspring.entity.PaperQuestion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PaperQuestionMapper {

    /**
     * 新增试卷题目
     * @param paperQuestions
     */
    void insert(@Param("paperQuestions") List<PaperQuestion> paperQuestions);


}
