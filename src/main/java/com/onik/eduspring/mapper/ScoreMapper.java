package com.onik.eduspring.mapper;

import com.onik.eduspring.entity.Score;
import com.onik.eduspring.vo.ScoreVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScoreMapper {

    /**
     * 查询所有成绩信息
     * @return
     */
    List<ScoreVo> getAll(Long userId);

    /**
     * 根据用户名查询成绩信息
     * @param username
     * @return
     */
    List<ScoreVo> getScoreByUserName(String username);

    /**
     * 根据课程查询成绩信息
     * @param title
     * @return
     */
    List<ScoreVo> getScoreByTitle(String title);

    /**
     * 根据id修改成绩信息
     * @param score
     */
    void setScore(Score score);
}
