package com.onik.eduspring.service;

import com.onik.eduspring.dto.HomeWorkDto;
import com.onik.eduspring.vo.HomeWorkVo;

import java.util.List;

public interface HomeWorkService {

    /**
     * 新增作业
     * @param homeWorkDto
     */
    void save(HomeWorkDto homeWorkDto);

    /**
     * 查询所有学生作业
     * @param homeWorkDto
     * @return
     */
    List<HomeWorkVo> getAll(HomeWorkDto homeWorkDto);

    /**
     * 根据学生id和课程id查询学生作业
     * @param homeWorkDto
     * @return
     */
    HomeWorkVo getByStudentIdAndCourseId(HomeWorkDto homeWorkDto);
}
