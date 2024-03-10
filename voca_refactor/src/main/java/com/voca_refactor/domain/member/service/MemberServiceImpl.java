package com.voca_refactor.domain.member.service;

import com.voca_refactor.domain.member.domain.Member;
import com.voca_refactor.domain.member.dto.MemberResponse;
import com.voca_refactor.domain.member.dto.RegisterDto;
import com.voca_refactor.domain.member.dto.SignInDto;
import com.voca_refactor.domain.member.repository.MemberRepository;
import com.voca_refactor.domain.security.config.dto.JwtToken;
import com.voca_refactor.domain.security.service.JwtTokenProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;

    @Override
    public JwtToken signIn(SignInDto form) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(form.getUsername(), form.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        JwtToken jwtToken = jwtTokenProvider.generateToken(authentication);

        return jwtToken;
    }

    @Override
    public Member register(RegisterDto form) {
        return memberRepository.save(form.toEntity());
    }

    @Override
    public MemberResponse getUserInfo(String username) {
        Member findMember = memberRepository.findByUsername(username).orElse(null);
        if (findMember != null) {
            return MemberResponse.builder()
                    .nickname(findMember.getNickname())
                    .wordMax(findMember.getWordMax())
                    .level(findMember.getLevel())
                    .build();
        }
        return null;
    }
}