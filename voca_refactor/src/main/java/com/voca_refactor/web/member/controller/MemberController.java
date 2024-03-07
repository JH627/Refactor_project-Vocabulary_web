package com.voca_refactor.web.member.controller;

import com.voca_refactor.domain.security.service.JwtTokenProviderService;
import com.voca_refactor.web.member.dto.MemberResponse;
import com.voca_refactor.web.member.dto.SignInDto;
import com.voca_refactor.web.member.util.response.CommonResponse;
import com.voca_refactor.web.member.util.response.SingleResponse;
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

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final JwtTokenProviderService jwtTokenProviderService;

    @PostMapping("/auth")
    public ResponseEntity<CommonResponse> signIn(HttpServletResponse response, @RequestBody SignInDto signInDto) {
        jwtTokenProviderService.setAccessToken(response, signInDto);
        return ResponseEntity.ok().body(new CommonResponse(HttpStatus.CREATED, "success"));
    }

    @GetMapping("/auth/user")
    public ResponseEntity<SingleResponse> home() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("userInfo called!");
        return ResponseEntity.ok().body
                (new SingleResponse<>(HttpStatus.ACCEPTED, "success!", new MemberResponse(user.getUsername(), 11, 22)));
    }

    @GetMapping("/logout")
    public ResponseEntity<CommonResponse> logout(HttpServletResponse response) {
        jwtTokenProviderService.logout(response);
        return ResponseEntity.ok().body(new CommonResponse(HttpStatus.ACCEPTED, "success"));
    }
}
