package com.voca_refactor.domain.member.dto;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class MemberResponse {
    private String nickname;
    private Integer level;
    private Integer wordMax;
}
