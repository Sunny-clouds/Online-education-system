package com.onik.eduspring.mapper;

import com.onik.eduspring.entity.HomeWork;
import com.onik.eduspring.vo.HomeWorkVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HomeWorkMapper {

    /**
     * 新增作业
     * @param homeWork
     */
    void save(HomeWork homeWork);

    /**
     * 查询所有学生的答案
     * @param homeWork
     * @return
     */
    List<HomeWorkVo> getAll(HomeWork homeWork);

    /**
     * 根据学生id和课程id查询作业
     * @param homeWork
     * @return
     */
    HomeWorkVo getByStudentIdAndCourseId(HomeWork homeWork);

    /**
     * 更新学生作业
     * @param homeWork
     */
    void update(HomeWork homeWork);
}
