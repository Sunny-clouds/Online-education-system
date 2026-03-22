package com.onik.eduspring.mapper;

import com.onik.eduspring.entity.CourseResource;
import com.onik.eduspring.vo.CourseResourceVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CourseResourceMapper {

    /**
     * 根据课程id获取课程资源
     * @param id
     * @return
     */
    List<CourseResourceVo> getCourseResourceById(Long id);

    /**
     * 根据id删除课程资源
     * @param id
     */
    void delCourseResourceById(Long id);

    /**
     * 上传课程资源
     * @param courseResource
     */
    void save(CourseResource courseResource);

    /**
     * 根据id获取课程资源URL
     * @param id
     * @return
     */
    @Select("select url from course_resource where id = #{id}")
    String getUrlById(Long id);
}
