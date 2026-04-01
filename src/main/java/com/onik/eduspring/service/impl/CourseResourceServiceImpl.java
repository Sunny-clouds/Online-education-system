package com.onik.eduspring.service.impl;

import com.onik.eduspring.dto.CourseResourceDto;
import com.onik.eduspring.entity.CourseResource;
import com.onik.eduspring.mapper.CourseResourceMapper;
import com.onik.eduspring.service.CourseResourceService;
import com.onik.eduspring.vo.CourseResourceVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CourseResourceServiceImpl implements CourseResourceService {

    @Autowired
    private CourseResourceMapper courseResourceMapper;
    //@Autowired
    //private AliyunOSSOperator ossOperator;

    /**
     * 根据课程id获取课程资源
     * @param id
     * @return
     */
    @Override
    public List<CourseResourceVo> getCourseResourceById(Long id) {
        return courseResourceMapper.getCourseResourceById(id);
    }

    /**
     * 根据id删除课程资源（同步删除 OSS 文件和数据库记录）
     */
    @Override
    @Transactional
    public void delCourseResourceById(Long id) {
        //// 1. 先查询资源记录，拿到 OSS 文件 URL
        //String fileUrl = courseResourceMapper.getUrlById(id);
        //if (fileUrl == null) {
        //    throw new RuntimeException("课程资源不存在");
        //}
        //// 2. 删除 OSS 文件
        //boolean ossDeleted = ossOperator.delete(fileUrl);
        //if (!ossDeleted) {
        //    throw new RuntimeException("OSS 文件删除失败");
        //}
        // 3. 删除数据库记录
        courseResourceMapper.delCourseResourceById(id);
    }

    /**
     * 上传课程资源
     * @param courseResourceDto
     */
    @Override
    @Transactional
    public void save(CourseResourceDto courseResourceDto) {
        CourseResource courseResource = new CourseResource();
        BeanUtils.copyProperties(courseResourceDto, courseResource);
        courseResource.setCreateTime(LocalDateTime.now());
        courseResourceMapper.save(courseResource);
    }
}
