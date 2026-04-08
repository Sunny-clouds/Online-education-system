package com.onik.eduspring.service;

import com.onik.eduspring.dto.PublishStudentExamDto;
import com.onik.eduspring.dto.StudentAnswerDto;

public interface StudentAnswerService {

    /**
     * 保存学生答案信息
     * @param studentAnswerDtos
     */
    void saveStudentAnswer(PublishStudentExamDto studentAnswerDtos);

    /**
     * 根据学生id和试卷id删除学生答案信息
     * @param studentAnswerDto
     */
    void delByStudentIdAndPaperId(StudentAnswerDto studentAnswerDto);
}
