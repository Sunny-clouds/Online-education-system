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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
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
     * @param id
     * @return
     */
    @Override
    public List<TestPaperVo> getTestPaperById(Long id, Long userId) {
        LocalDateTime now = LocalDateTime.now();
        Long activityId = examMapper.getExamId(id);
        LocalDateTime endTime = activityMapper.getEndTime(activityId);
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
    @Transactional
    @Override
    public void AutoGenerateTestPaper(TestPaperDto testPaperDto) {
        // 1、计算各题型目标分数
        int singleScore = (int)(testPaperDto.getTotalScore() * 0.5);
        int multiScore = (int)(testPaperDto.getTotalScore() * 0.3);
        int judgeScore = testPaperDto.getTotalScore() - singleScore - multiScore;

        // 2、按分数抽题
        List<Question> finalList = new ArrayList<>();
        finalList.addAll(selectQuestionsByScore(1, singleScore, testPaperDto.getCourseId()));
        finalList.addAll(selectQuestionsByScore(2, multiScore, testPaperDto.getCourseId()));
        finalList.addAll(selectQuestionsByScore(3, judgeScore, testPaperDto.getCourseId()));

        // 3、打乱整张试卷
        Collections.shuffle(finalList);
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

    /**
     * 根据分数获取题目
     * @param type 题型
     * @param targetScore 目标分数
     * @param courseId 课程id
     * @return
     */
    public List<Question> selectQuestionsByScore(int type, int targetScore, Long courseId) {
        // 1.根据课程id获取题库
        List<Question> questionBank = questionMapper.selectByType(type, courseId);
        if (questionBank.isEmpty()) {
            throw new RuntimeException("题库为空，type=" + type);
        }
        // 2. 打乱顺序（保证随机性）
        Collections.shuffle(questionBank);
        List<Question> result = new ArrayList<>();
        int total = 0;
        int tolerance = 2; // 允许误差
        // 3. 贪心算法选题（允许轻微超分）
        for (Question q : questionBank) {
            int nextTotal = total + q.getScore();
            // 如果加这题会超过“目标 + 最大超分”，就跳过
            if (nextTotal > targetScore + tolerance) {
                continue;
            }
            result.add(q);
            total += q.getScore();
            // 达到或超过目标就停止（允许超一点）
            if (total >= targetScore) {
                break;
            }
        }
        // 4. 容错机制（关键）
        if (Math.abs(total - targetScore) > tolerance) {
            throw new RuntimeException(
                    String.format("分数误差过大: type=%d, 目标=%d, 实际=%d", type, targetScore, total)
            );
        }
        return result;
    }
}
