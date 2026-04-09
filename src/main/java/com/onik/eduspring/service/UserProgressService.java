package com.onik.eduspring.service;

import com.onik.eduspring.dto.UserProgressDto;

public interface UserProgressService {

    /**
     * 设置用户课程进度
     * @param userProgressDto
     */
    void setProgressByVideoId(UserProgressDto userProgressDto);
}
