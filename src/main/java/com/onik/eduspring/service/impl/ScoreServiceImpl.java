package com.onik.eduspring.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.onik.eduspring.dto.ScoreDto;
import com.onik.eduspring.entity.PageResult;
import com.onik.eduspring.entity.Score;
import com.onik.eduspring.mapper.ScoreMapper;
import com.onik.eduspring.service.ScoreService;
import com.onik.eduspring.util.BaseContext;
import com.onik.eduspring.util.ScoreUtil;
import com.onik.eduspring.vo.ScoreVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreMapper scoreMapper;

    /**
     * 分页查询所有学生科目的成绩信息
     * @return
     */
    @Override
    public PageResult<ScoreVo> getAll(Integer pageNum, Integer pageSize) {
        // TODO 后期从考试类型活动里获取成绩
        Long userId = BaseContext.getUserId();
        PageHelper.startPage(pageNum, pageSize);
        List<ScoreVo> scores = scoreMapper.getAll(userId);
        PageInfo<ScoreVo> p = new PageInfo<>(scores);
        return new PageResult<ScoreVo>(p.getTotal(),p.getList());
    }

    /**
     * 根据学生姓名查询成绩
     * @param username
     * @return
     */
    @Override
    public List<ScoreVo> getScoreByUserName(String username) {
        List<ScoreVo> scores = scoreMapper.getScoreByUserName(username);
        return scores;
    }

    /**
     * 根据课程名查询成绩
     * @param title
     * @return
     */
    @Override
    public PageResult<ScoreVo> getScoreByTitle(String title,Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ScoreVo> scores = scoreMapper.getScoreByTitle(title);
        PageInfo<ScoreVo> p = new PageInfo<>(scores);
        return new PageResult<>(p.getTotal(),p.getList());
    }

    /**
     * 修改成绩
     * @param scoredto
     * @return
     */
    @Override
    @Transactional
    public void setScore(ScoreDto scoredto) {
        Score score = new Score();
        BeanUtils.copyProperties(scoredto,score);
        score.setTotalScore(scoredto.getUsualScore() * 0.4 + scoredto.getExamScore() * 0.6);
        score.setGradeLevel(ScoreUtil.getGradeLevel(score.getTotalScore()));
        scoreMapper.setScore(score);
    }
}
