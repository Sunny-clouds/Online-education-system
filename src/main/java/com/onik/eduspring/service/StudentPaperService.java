package com.onik.eduspring.service;

import com.onik.eduspring.entity.StudentPaper;
import com.onik.eduspring.vo.StudentPaperVo;

import java.util.List;

public interface StudentPaperService {

    /**
     *根据学生id和试卷id获取学生成绩
     * @param studentId
     * @param paperId
     * @return
     */
    StudentPaper getScoreByStudentIdAndPaperId(Long studentId, Long paperId);

    /**
     * 打回学生的考试信息
     * @param id
     */
    void delById(Long id);

    List<StudentPaperVo> getAllStudentPaper(Long activityId);

}
