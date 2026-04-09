package com.onik.eduspring.service.impl;

import com.onik.eduspring.dto.TestPaperDto;
import com.onik.eduspring.entity.PaperQuestion;
import com.onik.eduspring.entity.Question;
import com.onik.eduspring.entity.TestPaper;
import com.onik.eduspring.mapper.*;
import com.onik.eduspring.service.TestPaperService;
import com.onik.eduspring.vo.TestPaperVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TestPaperServiceImpl implements TestPaperService {

    @Autowired
    private TestPaperMapper testPaperMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private PaperQuestionMapper paperQuestionMapper;
    @Autowired
    private ExamMapper examMapper;
    @Autowired
    private StudentPaperMapper studentPaperMapper;
    @Autowired
    private ActivityMapper activityMapper;

    /**
     * 获取试卷信息
     *
     * @param id
     * @return
     */
    @Override
    public List<TestPaperVo> getTestPaperById(Long id, Long userId) {
        LocalDateTime now = LocalDateTime.now();
        Long examId = examMapper.getExamId(id);
        LocalDateTime endTime = activityMapper.getEndTime(examId);
        if (now.isAfter(endTime)) {
            throw new RuntimeException("考试已结束");
        }else{
            Integer max = examMapper.getMaxAttemptByPaperId(id);
            Integer attempt = studentPaperMapper.getAttemptByUseridAndPaperId(id,userId);
            if(attempt == null || attempt < max){
                return testPaperMapper.getTestPaperById(id);
            }
            throw new RuntimeException("您已超过最大尝试次数");
        }
    }

    /**
     * 新增试卷
     * @param testPaperDto
     */
    @Override
    public Long saveTestPaper(TestPaperDto testPaperDto) {
        TestPaper testPaper = new TestPaper();
        BeanUtils.copyProperties(testPaperDto,testPaper);
        testPaper.setCreateTime(LocalDateTime.now());
        testPaperMapper.saveTestPaper(testPaper);
        return testPaper.getId();
    }

    /**
     * 自动组成试卷
     * @param testPaperDto
     */
    @Override
    public void AutoGenerateTestPaper(TestPaperDto testPaperDto) {
        // 题型比例
        int singleTarget = (int)(testPaperDto.getTotalScore() * 0.5 / 2);
        int multiTarget = (int)(testPaperDto.getTotalScore() * 0.3 / 3);
        int judgeTarget = testPaperDto.getTotalScore() - singleTarget * 2 - multiTarget * 3;

        List<Question> finalList = new ArrayList<>();
        finalList.addAll(selectQuestionsByCount(1, singleTarget, testPaperDto.getCourseId()));
        finalList.addAll(selectQuestionsByCount(2, multiTarget, testPaperDto.getCourseId()));
        finalList.addAll(selectQuestionsByCount(3, judgeTarget, testPaperDto.getCourseId()));
        // 写入试卷
        List<PaperQuestion> paperQuestions = new ArrayList<>();
        int sort = 1;
        for (Question q : finalList) {
            PaperQuestion pq = new PaperQuestion();
            pq.setPaperId(testPaperDto.getId());
            pq.setQuestionId(q.getId());
            pq.setScore(q.getScore());
            pq.setSort(sort++);
            paperQuestions.add(pq);
        }
        paperQuestionMapper.insert(paperQuestions);
    }

    public List<Question> selectQuestionsByCount(int type, int count, Long courseId) {
        List<Question> questionBank = questionMapper.selectByType(type,count,courseId);
        if (questionBank.size() < count) {
            throw new RuntimeException("题库不足，无法抽题，type=" + type);
        }
        return questionBank;
    }
}
