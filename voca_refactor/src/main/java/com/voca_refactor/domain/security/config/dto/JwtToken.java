package com.voca_refactor.domain.security.config.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@AllArgsConstructor
@ToString
public class JwtToken {
    private String grantType;
    private String accessToken;
    private String refreshToken;
}
