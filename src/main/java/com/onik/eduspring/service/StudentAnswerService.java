package com.onik.eduspring.service;

import com.onik.eduspring.dto.PublishStudentExamDto;

public interface StudentAnswerService {

    /**
     * 保存学生答案信息
     *
     * @param studentAnswerDtos
     * @return
     */
    int saveStudentAnswer(PublishStudentExamDto studentAnswerDtos);
}
