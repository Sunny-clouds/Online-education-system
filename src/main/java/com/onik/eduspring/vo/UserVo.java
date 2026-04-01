package com.onik.eduspring.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserVo {

    private Long id;
    private String userName;
    private String nickName;
    private String email;
    private String phone;
    private String role;
    private String avatar;
    private Long status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
