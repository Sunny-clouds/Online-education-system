package com.onik.eduspring.mapper;

import com.onik.eduspring.entity.StudentPaper;
import com.onik.eduspring.vo.StudentPaperVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentPaperMapper {

    /**
     * 保存学生答卷信息
     * @param studentPaper
     */
    void save(StudentPaper studentPaper);

    /**
     * 根据试卷id和用户id获取用户答题次数
     * @param id
     * @param userId
     * @return
     */
    Integer getAttemptByUseridAndPaperId(Long id, Long userId);

    /**
     * 根据学生id和试卷id获取学生成绩
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

    /**
     * 获取学生的id和试卷id
     * @param id
     * @return
     */
    StudentPaper getStudentIdAndPaperId(Long id);

    /**
     * 获取所有学生的考试信息
     * @param activityId
     * @return
     */
    List<StudentPaperVo> getAllStudentPaper(Long activityId);

    /**
     * 修改学生考试信息
     * @param studentPaper
     */
    void update(StudentPaper studentPaper);
}
