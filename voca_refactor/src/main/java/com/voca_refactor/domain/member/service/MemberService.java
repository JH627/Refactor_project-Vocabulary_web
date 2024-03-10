package com.voca_refactor.domain.member.service;

import com.voca_refactor.domain.member.domain.Member;
import com.voca_refactor.domain.member.dto.MemberResponse;
import com.voca_refactor.domain.member.dto.RegisterDto;
import com.voca_refactor.domain.member.dto.SignInDto;
import com.voca_refactor.domain.security.config.dto.JwtToken;

public interface MemberService {
    JwtToken signIn(SignInDto form);
    Member register(RegisterDto form);
    MemberResponse getUserInfo(String username);
}