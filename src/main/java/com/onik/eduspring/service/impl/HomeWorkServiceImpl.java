package com.onik.eduspring.service.impl;

import com.onik.eduspring.dto.HomeWorkDto;
import com.onik.eduspring.entity.HomeWork;
import com.onik.eduspring.entity.StudentActivityRecord;
import com.onik.eduspring.mapper.ActivityMapper;
import com.onik.eduspring.mapper.HomeWorkMapper;
import com.onik.eduspring.mapper.StudentActivityRecordMapper;
import com.onik.eduspring.service.HomeWorkService;
import com.onik.eduspring.util.BaseContext;
import com.onik.eduspring.vo.HomeWorkVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HomeWorkServiceImpl implements HomeWorkService {

    @Autowired
    private HomeWorkMapper homeWorkMapper;
    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private StudentActivityRecordMapper studentActivityRecordMapper;

    /**
     * 新增作业
     * @param homeWorkDto
     */
    @Transactional
    @Override
    public void save(HomeWorkDto homeWorkDto) {
        Long userId = BaseContext.getUserId();
        HomeWork homeWork = new HomeWork();
        BeanUtils.copyProperties(homeWorkDto,homeWork);
        homeWork.setStudentId(userId);
        homeWork.setSubmitTime(LocalDateTime.now());
        HomeWorkVo homeWork1 =homeWorkMapper.getByStudentIdAndCourseId(homeWork);
        LocalDateTime startTime = activityMapper.getStartTime(homeWork.getActivityId());
        LocalDateTime endTime = activityMapper.getEndTime(homeWork.getActivityId());
        LocalDateTime now = LocalDateTime.now();
        StudentActivityRecord studentActivityRecord = new StudentActivityRecord();
        BeanUtils.copyProperties(homeWork,studentActivityRecord);
        studentActivityRecord.setScore(activityMapper.getScore(studentActivityRecord.getActivityId()));
        StudentActivityRecord s1 = studentActivityRecordMapper.getByStuIdAndCourseIdAndActivityId(studentActivityRecord);
        if(startTime.isBefore(now) && now.isBefore(endTime) && s1 == null){
            if(homeWork1 == null){
                homeWorkMapper.save(homeWork);
            }else {
                homeWorkMapper.update(homeWork);
            }
            studentActivityRecordMapper.save(studentActivityRecord);
        }else {
            throw new RuntimeException("活动未开始或活动已结束....");
        }

    }

    /**
     * 查询所有学生作业
     * @param homeWorkDto
     * @return
     */
    @Override
    public List<HomeWorkVo> getAll(HomeWorkDto homeWorkDto) {
        HomeWork homeWork = new HomeWork();
        BeanUtils.copyProperties(homeWorkDto,homeWork);
        return homeWorkMapper.getAll(homeWork);
    }

    /**
     * 根据学生id和课程id查询作业
     * @param homeWorkDto
     * @return
     */
    @Override
    public HomeWorkVo getByStudentIdAndCourseId(HomeWorkDto homeWorkDto) {
        Long userId = BaseContext.getUserId();
        HomeWork homeWork = new HomeWork();
        BeanUtils.copyProperties(homeWorkDto,homeWork);
        homeWork.setStudentId(userId);
        return homeWorkMapper.getByStudentIdAndCourseId(homeWork);
    }
}
