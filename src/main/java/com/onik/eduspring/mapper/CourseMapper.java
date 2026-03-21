package com.onik.eduspring.mapper;

import com.onik.eduspring.entity.Course;
import com.onik.eduspring.vo.CourseVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CourseMapper {

    /**
     * 查询所有课程信息
     * @return
     */
    @Select("select c.id, title, description, cover,u.nickname as teachername, c.create_time " +
            "from course c " +
            "left join user u on c.teacher_id = u.id")
    List<CourseVo> getAllCourse();

    /**
     * 根据课程名查询课程信息
     * @param title
     * @return
     */
    List<CourseVo> getCourseByTitle(String title);

    /**
     * 添加课程信息
     * @param course
     */
    void save(Course course);

    /**
     * 修改课程信息
     * @param course
     */
    void update(Course course);

    /**
     * 根据id删除课程信息
     * @param id
     */
    @Delete("delete from course where id = #{id}")
    void delById(Long id);
}
