package com.onik.eduspring.controller.user;


import com.onik.eduspring.dto.UserProgressDto;
import com.onik.eduspring.result.Result;
import com.onik.eduspring.service.UserProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/progress")
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
        return Result.success();
    }

}
