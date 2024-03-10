package com.voca_refactor.domain.member.dto;

import com.voca_refactor.domain.member.domain.Member;
import com.voca_refactor.domain.member.domain.Role;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class RegisterDto {

    private String username;
    private String password;
    private String nickname;
    private Integer wordMax;
    private Boolean social;

    public Member toEntity() {
        return Member.builder()
                .username(username)
                .password(password)
                .nickname(nickname)
                .wordMax(wordMax)
                .social(social)
                .level(1)
                .role(Role.USER.getValue())
                .build();
    }
}
