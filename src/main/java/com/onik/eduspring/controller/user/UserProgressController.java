package com.onik.eduspring.controller.user;


import com.onik.eduspring.dto.UserProgressDto;
import com.onik.eduspring.result.Result;
import com.onik.eduspring.service.UserProgressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/progress")
@Slf4j
public class UserProgressController {

    @Autowired
    private UserProgressService userProgressService;

    /**
     * 设置用户课程进度
     * @param userProgressDto
     * @return
     */
    @PreAuthorize("hasAnyAuthority('student')")
    @PostMapping("/saveProgress")
    public Result setProgressByVideoId(@RequestBody UserProgressDto userProgressDto) {
        userProgressService.setProgressByVideoId(userProgressDto);
        log.info("设置用户课程进度:{}",userProgressDto);
        return Result.success();
    }

}
