package com.onik.eduspring.service;

import com.onik.eduspring.vo.TestPaperVo;

import java.util.List;

public interface TestPaperService {

    /**
     * 获取试卷信息
     * @param id
     * @return
     */
    List<TestPaperVo> getTestPaperById(Long id);
}
