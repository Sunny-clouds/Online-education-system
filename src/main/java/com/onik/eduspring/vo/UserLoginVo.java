package com.onik.eduspring.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLoginVo {

    private Long id;
    private String username;
    private String nickname;
    private String email;
    private String phone;
    private String roleCode;
    private String avatar;
    private String token;
}
