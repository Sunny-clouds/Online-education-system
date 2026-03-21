package com.onik.eduspring.service;

import com.onik.eduspring.dto.ScoreDto;
import com.onik.eduspring.entity.PageResult;
import com.onik.eduspring.vo.ScoreVo;

import java.util.List;

public interface ScoreService {

    //查询所有成绩信息
    PageResult<ScoreVo> getAll(Integer pageNum, Integer pageSize);

    //根据用户名查询成绩信息
    List<ScoreVo> getScoreByUserName(String username);

    //根据课程名查询成绩信息
    PageResult<ScoreVo> getScoreByTitle(String title,Integer pageNum, Integer pageSize);

    //修改成绩信息
    void setScore(ScoreDto scoredto);
}
