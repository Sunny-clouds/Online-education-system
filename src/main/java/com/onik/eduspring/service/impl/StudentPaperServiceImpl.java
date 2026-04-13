package com.onik.eduspring.service.impl;

import com.onik.eduspring.dto.StudentAnswerDto;
import com.onik.eduspring.entity.StudentPaper;
import com.onik.eduspring.mapper.StudentPaperMapper;
import com.onik.eduspring.service.StudentAnswerService;
import com.onik.eduspring.service.StudentPaperService;
import com.onik.eduspring.vo.StudentPaperVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentPaperServiceImpl implements StudentPaperService {

    @Autowired
    private StudentPaperMapper studentPaperMapper;
    @Autowired
    private StudentAnswerService studentAnswerService;

    /**
     *根据学生id和试卷id获取学生成绩
     * @param studentId
     * @param paperId
     * @return
     */
    @Override
    public StudentPaper getScoreByStudentIdAndPaperId(Long studentId, Long paperId) {
        return studentPaperMapper.getScoreByStudentIdAndPaperId(studentId,paperId);
    }

    /**
     * 打回学生的考试信息
     * @param id
     */
    @Transactional
    @Override
    public void delById(Long id) {
        StudentPaper studentPaper = studentPaperMapper.getStudentIdAndPaperId(id);
        StudentAnswerDto studentAnswerDto = new StudentAnswerDto();
        studentAnswerDto.setStudentId(studentPaper.getStudentId());
        studentAnswerDto.setStudentPaperId(studentPaper.getPaperId());
        studentAnswerService.delByStudentIdAndPaperId(studentAnswerDto);
        studentPaperMapper.delById(id);
    }

    /**
     * 获取所有学生的考试信息
     * @param activityId
     * @return
     */
    @Override
    public List<StudentPaperVo> getAllStudentPaper(Long activityId) {
        return studentPaperMapper.getAllStudentPaper(activityId);
    }
}
