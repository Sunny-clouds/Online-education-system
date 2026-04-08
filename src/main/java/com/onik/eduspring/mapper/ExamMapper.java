package com.onik.eduspring.mapper;

import com.onik.eduspring.entity.Exam;
import com.onik.eduspring.vo.ExamVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExamMapper {

    /**
     * 根据活动id获取考试信息
     * @param bizId
     * @return
     */
    ExamVo getExamByBizId(Long bizId);

    /**
     * 新增考试信息
     * @param exam
     */
    void saveExam(Exam exam);

    /**
     * 修改考试信息
     * @param exam
     */
    void updateExam(Exam exam);

    /**
     * 根据试卷id获取最大尝试次数
     * @param id
     * @return
     */
    Integer getMaxAttemptByPaperId(Long id);
}
