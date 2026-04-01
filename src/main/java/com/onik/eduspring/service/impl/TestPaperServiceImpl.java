package com.onik.eduspring.service.impl;

import com.onik.eduspring.mapper.TestPaperMapper;
import com.onik.eduspring.service.TestPaperService;
import com.onik.eduspring.vo.TestPaperVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestPaperServiceImpl implements TestPaperService {

    @Autowired
    private TestPaperMapper testPaperMapper;

    /**
     * 获取试卷信息
     * @param id
     * @return
     */
    @Override
    public List<TestPaperVo> getTestPaperById(Long id) {
        return testPaperMapper.getTestPaperById(id);
    }
}
