package com.onik.eduspring.service;

import com.onik.eduspring.dto.PublishStudentExamDto;
import com.onik.eduspring.vo.StudentAnswerVo;

import java.util.List;

public interface StudentAnswerService {

    /**
     * 保存学生答案信息
     *
     * @param studentAnswerDtos
     * @return
     */
    int saveStudentAnswer(PublishStudentExamDto studentAnswerDtos);

    /**
     * 根据试卷ID获取学生答案信息
     * @param paperId
     * @return
     */
    List<StudentAnswerVo> getStudentAnswerByPaperId(Long paperId);
}
