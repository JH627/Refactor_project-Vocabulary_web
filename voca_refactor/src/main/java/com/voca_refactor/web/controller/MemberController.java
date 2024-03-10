package com.voca_refactor.web.controller;

import com.voca_refactor.domain.member.domain.Member;
import com.voca_refactor.domain.member.dto.MemberResponse;
import com.voca_refactor.domain.member.dto.RegisterDto;
import com.voca_refactor.domain.member.dto.SignInDto;
import com.voca_refactor.domain.member.service.MemberService;
import com.voca_refactor.domain.security.service.JwtTokenProviderService;
import com.voca_refactor.web.util.response.CommonResponse;
import com.voca_refactor.web.util.response.SingleResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * todo validation, 예외처리 추가
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final JwtTokenProviderService jwtTokenProviderService;

    @PostMapping("/auth/new")
    public ResponseEntity<CommonResponse> register(@RequestBody RegisterDto form) {
        Member register = memberService.register(form);
        return ResponseEntity.ok().body(
                new CommonResponse(HttpStatus.CREATED, "register success!")
        );
    }

    @PostMapping("/auth")
    public ResponseEntity<CommonResponse> signIn(HttpServletResponse response, @RequestBody SignInDto form) {
        jwtTokenProviderService.setAccessToken(response, form);
        return ResponseEntity.ok().body(new CommonResponse(HttpStatus.CREATED, "auth success"));
    }

    @GetMapping("/auth/user")
    public ResponseEntity<SingleResponse<MemberResponse>> home() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok().body
                (new SingleResponse<>(HttpStatus.ACCEPTED, "success!", memberService.getUserInfo(user.getUsername())));
    }

    @GetMapping("/logout")
    public ResponseEntity<CommonResponse> logout(HttpServletResponse response) {
        jwtTokenProviderService.logout(response);
        return ResponseEntity.ok().body(new CommonResponse(HttpStatus.ACCEPTED, "logout success"));
    }
}