package com.voca_refactor.domain.member.service;

import com.voca_refactor.domain.security.config.dto.JwtToken;

public interface MemberService {
    JwtToken signIn(String username, String password);
}