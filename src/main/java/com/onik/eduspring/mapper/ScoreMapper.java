package com.onik.eduspring.mapper;

import com.onik.eduspring.entity.Score;
import com.onik.eduspring.vo.ScoreVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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

    /**
     * 添加成绩信息
     * @param score
     */
    void save(Score score);

    /**
     * 更新成绩信息
     * @param score
     */
    void updateScore(Score score);

    /**
     * 根据id查询考试分数
     * @param id
     * @return
     */
    @Select("select exam_score from score where id = #{id}")
    Double getExamScoreById(Long id);

    /**
     * 根据用户名查询成绩信息（老师）
     * @param username
     * @return
     */
    List<ScoreVo> getScoreByNameTeacher(String username);
}
