package com.onik.eduspring.service.impl;

import com.onik.eduspring.dto.UserProgressDto;
import com.onik.eduspring.entity.UserProgress;
import com.onik.eduspring.mapper.CourseResourceMapper;
import com.onik.eduspring.mapper.StudentCourseMapper;
import com.onik.eduspring.mapper.UserMapper;
import com.onik.eduspring.mapper.UserProgressMapper;
import com.onik.eduspring.service.UserProgressService;
import com.onik.eduspring.util.BaseContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserProgressServiceImpl implements UserProgressService {

    @Autowired
    private UserProgressMapper userProgressMapper;
    @Autowired
    private CourseResourceMapper courseResourceMapper;
    @Autowired
    private StudentCourseMapper studentCourseMapper;
    @Autowired
    private UserMapper userMapper;

    /**
     * 设置用户课程进度
     * @param userProgressDto
     */
    @Transactional
    @Override
    public void setProgressByVideoId(UserProgressDto userProgressDto) {
        UserProgress userProgress = new UserProgress();
        Long userId = BaseContext.getUserId();
        Integer role = userMapper.getUserRoleById(userId);
        if (role == 3){
            userProgressDto.setStudentId(userId);
            BeanUtils.copyProperties(userProgressDto,userProgress);
            //获取用户视频进度
            Double progress = userProgressMapper.getProgressByVideoId(userProgress);
            //获取视频时长
            Long duration = courseResourceMapper.getDurationByVideoId(userProgress.getVideoId());
            Double p = userProgressDto.getCurrentTime() * 100.0 / duration;
            userProgress.setProgress(p);
            userProgress.setLastWatchTime(LocalDateTime.now());
            if (progress == null){
                userProgressMapper.save(userProgress);
            }else if (p > progress) {
                userProgressMapper.update(userProgress);
            }
            //根据课程id查看视频总时长
            Long sumDuration = courseResourceMapper.getDurationByCourseId(userProgressDto.getCourseId());
            //获取用户视频大于90的视频id
            List<Long> videoId =userProgressMapper.getVideoId(userId);
            if (!videoId.isEmpty()){
                //根据大于90的视频id获取总时长
                Long oldDuration = courseResourceMapper.getDurationByListVideoId(videoId);
                userProgressDto.setProgress(oldDuration * 100.0 / sumDuration);
                userProgressDto.setStatus(userProgressDto.getProgress() > 90 ? 2 : 1);
                studentCourseMapper.updateProgress(userProgressDto);
            }
        }
    }
}
