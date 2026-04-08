package com.onik.eduspring.service.impl;

import com.onik.eduspring.dto.PublishStudentExamDto;
import com.onik.eduspring.dto.StudentAnswerDto;
import com.onik.eduspring.entity.StudentAnswer;
import com.onik.eduspring.mapper.QuestionMapper;
import com.onik.eduspring.mapper.StudentAnswerMapper;
import com.onik.eduspring.mapper.StudentPaperMapper;
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

    /**
     * 保存学生的答案和试卷信息
     * @param studentAnswerDtos
     */
    @Transactional
    @Override
    public void saveStudentAnswer(PublishStudentExamDto studentAnswerDtos) {
        //Long userId = BaseContext.getUserId();
        List<StudentAnswerDto> answerDtos = studentAnswerDtos.getStudentAnswerDtos();
        List<Long> questionIds = answerDtos.stream()
                .map(StudentAnswerDto::getQuestionId)
                .collect(Collectors.toList());
        List<StudentAnswerDto> correctList = questionMapper.getExamTitleAnswerById(questionIds);

        Map<Long, StudentAnswerDto> correctMap = correctList.stream()
                .collect(Collectors.toMap(
                        StudentAnswerDto::getQuestionId,
                        dto -> dto
                ));
        List<StudentAnswer> studentAnswers = new ArrayList<>();
        for (StudentAnswerDto dto : answerDtos) {
            StudentAnswer sa = new StudentAnswer();
            BeanUtils.copyProperties(dto, sa);
            //sa.setStudentId(userId);
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
        StudentAnswerDto studentAnswer = studentAnswerMapper.getByStudentIdAndPaperId(first);
        if (studentAnswer == null){
            studentAnswerMapper.save(studentAnswers);
            StudentAnswerDto answerDto = studentAnswerMapper.getByStudentIdAndPaperId(first);
            studentAnswerDtos.getStudentPaper().setTotalScore(answerDto.getCountScore());
            studentAnswerDtos.getStudentPaper().setAttempt(attempt++);
        }
        studentPaperMapper.save(studentAnswerDtos.getStudentPaper());

        //studentAnswerMapper.delByStudentIdAndPaperId(first);


    }

    /**
     * 根据学生id和试卷id删除学生答案信息
     * @param studentAnswerDto
     */
    @Override
    public void delByStudentIdAndPaperId(StudentAnswerDto studentAnswerDto) {
        studentAnswerMapper.delByStudentIdAndPaperId(studentAnswerDto);
    }
}
