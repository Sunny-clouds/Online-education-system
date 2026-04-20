package com.onik.eduspring.controller.admin;


import com.onik.eduspring.result.Result;
import com.onik.eduspring.service.TestPaperService;
import com.onik.eduspring.util.BaseContext;
import com.onik.eduspring.vo.TestPaperVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 接收试卷请求
 */
@RestController
@RequestMapping("/api/testPaper")
@Slf4j
@Tag(name = "试卷管理")
public class TestPaperController {

    @Autowired
    private TestPaperService testPaperService;

    /**
     * 获取试卷信息
     * @param id
     * @return
     */
    @GetMapping("/getTestPaperById/{id}")
    @Operation(summary = "获取试卷信息")
    public Result getTestPaperById(@PathVariable Long id){
        Long userId = BaseContext.getUserId();
        List<TestPaperVo> testPaperVo = testPaperService.getTestPaperById(id,userId);
        log.info("获取试卷信息成功:{}", testPaperVo.size());
        return Result.success(testPaperVo);
    }


    ///**
    // * 新增试卷
    // * @param testPaperDto
    // * @return
    // */
    //@PreAuthorize("hasAnyAuthority('admin','teacher')")
    //@PostMapping("/saveTestPaper")
    //public Result saveTestPaper(@RequestBody TestPaperDto testPaperDto){
    //    Long id = testPaperService.saveTestPaper(testPaperDto);
    //    return Result.success(id);
    //}

    ///**
    // * 自动组成试卷
    // * @return
    // */
    //@PreAuthorize("hasAnyAuthority('admin','teacher')")
    //@PostMapping("/autoGenerateTestPaper")
    //public Result AutoGenerateTestPaper (@RequestBody TestPaperDto testPaperDto){
    //    testPaperService.AutoGenerateTestPaper(testPaperDto);
    //    return Result.success();
    //}
}
