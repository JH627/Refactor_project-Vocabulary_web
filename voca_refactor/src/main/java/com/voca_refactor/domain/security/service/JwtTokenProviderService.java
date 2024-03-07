package com.voca_refactor.domain.security.service;

import com.voca_refactor.domain.member.service.MemberService;
import com.voca_refactor.domain.security.config.dto.JwtToken;
import com.voca_refactor.web.member.dto.SignInDto;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtTokenProviderService {

    private final JwtTokenProvider tokenProvider;
    private final MemberService memberService;

    private static final String AUTH_HEADER = "Authorization";
    private static final String GRANT_TYPE = "Grant-Type";

    // 추후에 web package 참조하지 않도록 변경
    public void setAccessToken(HttpServletResponse response, SignInDto signInDto) {
        JwtToken jwtToken = memberService.signIn(signInDto.getUsername(), signInDto.getPassword());
        response.setHeader(AUTH_HEADER, jwtToken.getAccessToken());
        tokenProvider.setHttpOnlyCookie(response, jwtToken.getRefreshToken());
    }

    public void logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("refreshToken", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
