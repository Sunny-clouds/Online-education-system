package com.onik.eduspring.service;

import com.onik.eduspring.dto.UserProgressDto;
import com.onik.eduspring.entity.UserProgress;

import java.util.List;

public interface UserProgressService {

    /**
     * 设置用户课程进度
     * @param userProgressDto
     */
    void setProgressByVideoId(UserProgressDto userProgressDto);

    /**
     * 根据学生id获取视频进度
     * @param userProgressDto
     * @return
     */
    List<UserProgress> getProgressByStuIdAndVideoId(UserProgressDto userProgressDto);
}
