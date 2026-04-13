package com.onik.eduspring.mapper;

import com.onik.eduspring.entity.StudentActivityRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentActivityRecordMapper {

    /**
     * 保存学生活动记录
     * @param studentActivityRecord
     */
    void save(StudentActivityRecord studentActivityRecord);

    /**
     * 删除学生活动记录
     * @param studentActivityRecord
     */
    void delByStudentId(StudentActivityRecord studentActivityRecord);

    /**
     * 退课后删除所有活动记录
     * @param userId
     * @param courseId
     */
    void delByStudentIdandCourseId(Long userId, Long courseId);
}
