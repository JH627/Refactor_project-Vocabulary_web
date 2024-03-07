package com.voca_refactor.web.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MemberResponse {
    private String nickname;
    private Integer level;
    private Integer wordMax;
}
