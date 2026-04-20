package com.onik.eduspring.service.impl;

import com.onik.eduspring.dto.PublishStudentExamDto;
import com.onik.eduspring.dto.StudentAnswerDto;
import com.onik.eduspring.entity.Score;
import com.onik.eduspring.entity.StudentActivityRecord;
import com.onik.eduspring.entity.StudentAnswer;
import com.onik.eduspring.entity.TestPaper;
import com.onik.eduspring.mapper.*;
import com.onik.eduspring.service.StudentAnswerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class StudentAnswerServiceImpl implements StudentAnswerService {

    @Autowired
    private StudentAnswerMapper studentAnswerMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private StudentPaperMapper studentPaperMapper;
    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private StudentActivityRecordMapper studentActivityRecordMapper;
    @Autowired
    private ScoreMapper scoreMapper;
    @Autowired
    private TestPaperMapper testPaperMapper;

    /**
     * 保存学生的答案和试卷信息
     * @param studentAnswerDtos
     * @return 考试分数
     */
    @Transactional
    @Override
    public int saveStudentAnswer(PublishStudentExamDto studentAnswerDtos) {
        //Long userId = BaseContext.getUserId();
        List<StudentAnswerDto> answerDtos = studentAnswerDtos.getStudentAnswerDtos();
        //提取所有题目ID
        List<Long> questionIds = answerDtos.stream()
                .map(StudentAnswerDto::getQuestionId)
                .collect(Collectors.toList());
        //根据题id查询正确答案
        List<StudentAnswerDto> correctList = questionMapper.getExamTitleAnswerById(questionIds);
        //将题目ID作为键，答案作为值
        Map<Long, StudentAnswerDto> correctMap = correctList.stream()
                .collect(Collectors.toMap(
                        StudentAnswerDto::getQuestionId,
                        dto -> dto
                ));
        List<StudentAnswer> studentAnswers = new ArrayList<>();
        //遍历学生答案并自动判分
        for (StudentAnswerDto dto : answerDtos) {
            StudentAnswer sa = new StudentAnswer();
            BeanUtils.copyProperties(dto, sa);
            //sa.setStudentId(userId);
            //获取正确答案
            StudentAnswerDto correctDto = correctMap.get(dto.getQuestionId());
            sa.setCorrectAnswer(correctDto.getCorrectAnswer());
            if (sa.getStudentAnswer() != null && sa.getStudentAnswer().equals(sa.getCorrectAnswer())) {
                sa.setScore(correctDto.getScore());
                sa.setIsCorrect(1);
            }else {
                sa.setScore(0);
                sa.setIsCorrect(0);
            }
            studentAnswers.add(sa);
        }
        int attempt = 1;
        StudentAnswerDto first = answerDtos.getFirst();
        //查询是否已经做过这套试卷
        StudentAnswerDto studentAnswer = studentAnswerMapper.getByStudentIdAndPaperId(first);
        //获取学生分数
        int totalScore = studentAnswers.stream()
                .mapToInt(StudentAnswer::getScore)
                .sum();
        StudentActivityRecord studentActivityRecord = new StudentActivityRecord();
        BeanUtils.copyProperties(studentAnswerDtos.getStudentPaper(), studentActivityRecord);
        studentActivityRecord.setScore(activityMapper.getScore(studentActivityRecord.getActivityId()));
        studentActivityRecord.setCourseId(activityMapper.getCourseIdById(studentActivityRecord.getActivityId()));
        // 判断是否已经参加过活动
        StudentActivityRecord s1 = studentActivityRecordMapper.getByStuIdAndCourseIdAndActivityId(studentActivityRecord);
        Long studentId = studentAnswerDtos.getStudentPaper().getStudentId();
        Long paperId = studentAnswerDtos.getStudentPaper().getPaperId();
        Score score = new Score();
        score.setStudentId(studentId);
        score.setCourseId(activityMapper.getCourseIdById(studentAnswerDtos.getStudentPaper().getActivityId()));
        score.setExamScore((double) totalScore);
        TestPaper testPaper = testPaperMapper.getTitleAndTeaById(paperId);
        score.setExamName(testPaper.getTitle());
        score.setTeacherId(testPaper.getCreateUser());
        if (s1 ==  null){studentActivityRecordMapper.save(studentActivityRecord);}
        // 判断是否是第一次考试
        if (studentAnswer == null){
            studentAnswerMapper.save(studentAnswers);
            StudentAnswerDto answerDto = studentAnswerMapper.getByStudentIdAndPaperId(first);
            studentAnswerDtos.getStudentPaper().setTotalScore(answerDto.getCountScore());
            studentAnswerDtos.getStudentPaper().setAttempt(attempt);
            studentPaperMapper.save(studentAnswerDtos.getStudentPaper());
            scoreMapper.save(score);
            return totalScore;
        }else if (studentAnswer.getCountScore() < totalScore){
            studentAnswerMapper.delByStudentIdAndPaperId(studentAnswer);
            studentAnswerMapper.save(studentAnswers);
            studentAnswerDtos.getStudentPaper().setTotalScore(totalScore);
        }
        int attempts = studentPaperMapper.getAttemptByUseridAndPaperId(paperId , studentId);
        studentAnswerDtos.getStudentPaper().setAttempt(attempts + 1);
        studentPaperMapper.update(studentAnswerDtos.getStudentPaper());
        scoreMapper.updateScore(score);
        return totalScore;
    }
}
