package com.onik.eduspring.service;

import com.onik.eduspring.dto.PublishExamDto;

public interface ExamActivityService {

    /**
     * 创建考试活动
     * @param dto
     */
    void publishExamActivity(PublishExamDto dto);
}
