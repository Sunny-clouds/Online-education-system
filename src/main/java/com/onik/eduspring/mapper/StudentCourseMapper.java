package com.onik.eduspring.mapper;


import com.onik.eduspring.entity.StudentCourse;
import com.onik.eduspring.vo.StudentCourseVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentCourseMapper {

    /**
     * 获取所有学生选课信息
     * @return
     */
    List<StudentCourseVo> getAll();

    /**
     * 根据学生姓名查询选课信息
     * @param nickname
     * @return
     */
    List<StudentCourseVo> getByUserName(String nickname);

    /**
     * 根据选课id删除选课信息
     * @param id
     */
    void delByUserName(Long id);

    /**
     * 保存选课信息
     * @param studentCourse
     */
    void save(StudentCourse studentCourse);

    /**
     * 修改课程进度
     * @param studentCourse
     */
    void update(StudentCourse studentCourse);

    /**
     * 根据学生id删除选课信息
     * @param id
     */
    void delByStudentId(Long id);
}
