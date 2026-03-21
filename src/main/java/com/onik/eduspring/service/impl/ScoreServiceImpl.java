package com.onik.eduspring.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.onik.eduspring.dto.ScoreDto;
import com.onik.eduspring.entity.PageResult;
import com.onik.eduspring.entity.Score;
import com.onik.eduspring.mapper.ScoreMapper;
import com.onik.eduspring.service.ScoreService;
import com.onik.eduspring.util.ScoreUtil;
import com.onik.eduspring.vo.ScoreVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreMapper scoreMapper;

    @Override
    public PageResult<ScoreVo> getAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ScoreVo> scores = scoreMapper.getAll();
        PageInfo<ScoreVo> p = new PageInfo<>(scores);
        return new PageResult<ScoreVo>(p.getTotal(),p.getList());
    }

    @Override
    public List<ScoreVo> getScoreByUserName(String username) {
        List<ScoreVo> scores = scoreMapper.getScoreByUserName(username);
        return scores;
    }

    @Override
    public PageResult<ScoreVo> getScoreByTitle(String title,Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ScoreVo> scores = scoreMapper.getScoreByTitle(title);
        PageInfo<ScoreVo> p = new PageInfo<>(scores);
        return new PageResult<>(p.getTotal(),p.getList());
    }

    @Override
    public void setScore(ScoreDto scoredto) {
        Score score = new Score();
        BeanUtils.copyProperties(scoredto,score);
        score.setTotalScore(scoredto.getUsualScore() * 0.4 + scoredto.getExamScore() * 0.6);
        score.setGradeLevel(ScoreUtil.getGradeLevel(score.getTotalScore()));
        scoreMapper.setScore(score);
    }
}
