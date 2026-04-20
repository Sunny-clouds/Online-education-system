package com.onik.eduspring.mapper;


import com.onik.eduspring.entity.TestPaper;
import com.onik.eduspring.vo.TestPaperVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TestPaperMapper {

    /**
     * 根据id查询试卷信息
     * @param id
     * @return
     */
    List<TestPaperVo> getTestPaperById(Long id);

    /**
     * 新增试卷
     * @param testPaper
     */
    void saveTestPaper(TestPaper testPaper);

    /**
     * 自动组成试卷
     * @param id
     * @param realTotal
     */
    void updateTotalScore(Long id, int realTotal);

    /**
     * 根据试卷id查询试卷标题
     * @param paperId
     * @return
     */
    @Select("select title,create_user from test_paper where id = #{paperId}")
    TestPaper getTitleAndTeaById(Long paperId);
}
