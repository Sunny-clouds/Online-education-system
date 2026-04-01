package com.onik.eduspring.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.onik.eduspring.dto.StudentCourseDto;
import com.onik.eduspring.dto.StudentCourseStatusDto;
import com.onik.eduspring.entity.PageResult;
import com.onik.eduspring.entity.StudentCourse;
import com.onik.eduspring.mapper.StudentCourseMapper;
import com.onik.eduspring.result.Result;
import com.onik.eduspring.service.StudentCourseService;
import com.onik.eduspring.vo.StudentCourseVo;
import com.onik.eduspring.vo.StudentsCourseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class StudentCourseServiceImpl implements StudentCourseService {

    @Autowired
    private StudentCourseMapper studentCourseMapper;


    /**
     * 获取所有学生选课信息
     * @return
     */
    @Override
    public PageResult<StudentCourseVo> getAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<StudentCourseVo> list = studentCourseMapper.getAll();
        PageInfo<StudentCourseVo> p = new PageInfo<>(list);
        return new PageResult<>(p.getTotal(),p.getList());
    }

    /**
     * 根据学生姓名查询选课信息
     * @param nickname
     * @return
     */
    @Override
    public List<StudentCourseVo> getByUserName(String nickname) {
        List<StudentCourseVo> list = studentCourseMapper.getByUserName(nickname);
        return list;
    }

    /**
     * 根据选课id删除选课信息
     * @param id
     */
    @Transactional
    @Override
    public void delByUserName(Long id) {
        studentCourseMapper.delByUserName(id);
    }

    /**
     * 添加选课信息
     * @param studentCourseDto
     */
    @Transactional
    @Override
    public Result save(StudentCourseDto studentCourseDto) {
        StudentCourse student = studentCourseMapper.getByIdAndCourseId(studentCourseDto);
        if (student == null) {
            StudentCourse studentCourse = new StudentCourse();
            BeanUtils.copyProperties(studentCourseDto, studentCourse);
            studentCourse.setSelectTime(LocalDateTime.now());
            studentCourse.setStatus(1L);
            studentCourse.setProgress(0L);
            studentCourseMapper.save(studentCourse);
            return Result.success();
        }
        return Result.error("已经选过了，不能再选了");
    }

    /**
     * 修改选课进度
     * @param statusDto
     */
    @Override
    @Transactional
    public void update(StudentCourseStatusDto statusDto) {
        StudentCourse studentCourse = new StudentCourse();
        BeanUtils.copyProperties(statusDto, studentCourse);
        if(statusDto.getProgress() == 100L){
            studentCourse.setStatus(2L);
            studentCourse.setProgress(100L);
        }else{
            studentCourse.setStatus(1L);
            studentCourse.setProgress(statusDto.getProgress());
        }
        studentCourse.setSelectTime(LocalDateTime.now());
        studentCourseMapper.update(studentCourse);
    }

    /**
     * 根据选课课程id查询所有学生
     * @param id
     * @return
     */
    @Override
    public PageResult<StudentsCourseVo> getCourseById(Long id,Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<StudentsCourseVo> list = studentCourseMapper.getCourseById(id);
        PageInfo<StudentsCourseVo> p = new PageInfo<>(list);
        return new PageResult<>(p.getTotal(),p.getList());
    }
}
