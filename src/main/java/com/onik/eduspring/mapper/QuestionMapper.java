package com.onik.eduspring.mapper;

import com.onik.eduspring.dto.StudentAnswerDto;
import com.onik.eduspring.entity.Question;
import com.onik.eduspring.vo.QuestionVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuestionMapper {

    /**
     * 新增题目
     * @param question
     */
    void saveExamTitle(Question question);

    /**
     * 修改题目
     * @param question
     */
    void updateExamTitle(Question question);

    /**
     * 获取题库所有题目信息
     * @return
     */
    List<QuestionVo> getAllExamTitle();

    /**
     * 根据id删除题目
     * @param id
     */
    void delExamTitle(Long id);

    /**
     * 获取该题型全部题目
     * @param type
     * @return
     */
    List<Question> selectByType(int type, Long courseId);

    /**
     * 根据题目id获取正确答案
     * @param id
     * @return
     */
    List<StudentAnswerDto> getExamTitleAnswerById(List<Long> id);
}
