package com.onik.eduspring.mapper;


import com.onik.eduspring.vo.TestPaperVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestPaperMapper {

    /**
     * 根据id查询试卷信息
     * @param id
     * @return
     */
    List<TestPaperVo> getTestPaperById(Long id);
}
