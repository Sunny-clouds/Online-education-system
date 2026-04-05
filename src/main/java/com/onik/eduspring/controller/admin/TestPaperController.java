package com.onik.eduspring.controller.admin;


import com.onik.eduspring.dto.TestPaperDto;
import com.onik.eduspring.result.Result;
import com.onik.eduspring.service.TestPaperService;
import com.onik.eduspring.vo.TestPaperVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 接收试卷请求
 */
@RestController
@RequestMapping("/api/testPaper")
@Slf4j
public class TestPaperController {

    @Autowired
    private TestPaperService testPaperService;

    /**
     * 获取试卷信息
     * @param id
     * @return
     */
    @GetMapping("/getTestPaperById/{id}")
    public Result getTestPaperById(@PathVariable Long id){
        List<TestPaperVo> testPaperVo = testPaperService.getTestPaperById(id);
        return Result.success(testPaperVo);
    }

    /**
     * 新增试卷
     * @param testPaperDto
     * @return
     */
    @PreAuthorize("hasAnyAuthority('admin','teacher')")
    @PostMapping("/saveTestPaper")
    public Result saveTestPaper(@RequestBody TestPaperDto testPaperDto){
        Long id = testPaperService.saveTestPaper(testPaperDto);
        return Result.success(id);
    }
}
