package com.onik.eduspring.mapper;

import com.onik.eduspring.dto.StudentAnswerDto;
import com.onik.eduspring.entity.StudentAnswer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentAnswerMapper {

    /**
     * 保存学生的答案信息
     *
     * @param studentAnswers
     */
    void save(List<StudentAnswer> studentAnswers);

    /**
     * 查询学生历史的答卷信息
     *
     * @param first
     */
    StudentAnswerDto getByStudentIdAndPaperId(StudentAnswerDto first);

    /**
     * 删除学生历史的答卷信息
     * @param first
     */
    void delByStudentIdAndPaperId(StudentAnswerDto first);
}
