package com.onik.eduspring.mapper;

import com.onik.eduspring.entity.UserProgress;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserProgressMapper {

    /**
     * 根据视频id获取用户课程进度
     * @param userProgress
     * @return
     */
    Double getProgressByVideoId(UserProgress userProgress);

    /**
     * 保存用户课程进度
     * @param userProgress
     */
    void save(UserProgress userProgress);

    /**
     * 修改用户课程进度
     * @param userProgress
     */
    void update(UserProgress userProgress);

    /**
     * 获取进度大于90的视频id
     * @return
     */
    List<Long> getVideoId(Long userId);

}
