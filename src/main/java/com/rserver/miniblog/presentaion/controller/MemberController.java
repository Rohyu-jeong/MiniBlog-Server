package com.rserver.miniblog.presentaion.controller;

import com.rserver.miniblog.application.dto.request.MemberUpdateRequestDto;
import com.rserver.miniblog.application.dto.request.NicknameRequestDto;
import com.rserver.miniblog.application.dto.request.PasswordUpdateRequestDto;
import com.rserver.miniblog.application.dto.request.SignUpRequestDto;
import com.rserver.miniblog.application.dto.response.MemberResponseDto;
import com.rserver.miniblog.application.dto.internal.NicknameInfo;
import com.rserver.miniblog.application.service.member.AccountService;
import com.rserver.miniblog.infrastructure.security.MemberDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/members")
@Validated
@RequiredArgsConstructor
public class MemberController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<String> createMember(
            @Valid @RequestBody SignUpRequestDto requestDto
    ) {
        accountService.register(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("회원가입이 성공적으로 완료되었습니다.");
    }

    @PostMapping("/nickname")
    public ResponseEntity<NicknameInfo> createNickname(
            @Valid @RequestBody NicknameRequestDto requestDto,
            @AuthenticationPrincipal MemberDetails memberDetails
    ) {
        accountService.addNickname(memberDetails.getMember().getId(), requestDto.getNickname());

        return ResponseEntity.status(HttpStatus.CREATED).body(NicknameInfo.of(true, requestDto.getNickname()));
    }

    @PatchMapping("/password")
    public ResponseEntity<String> updatePassword(
            @Valid @RequestBody PasswordUpdateRequestDto requestDto,
            @AuthenticationPrincipal MemberDetails memberDetails
    ) {
        accountService.updatePassword(memberDetails.getMember().getId(), requestDto);

        return ResponseEntity.ok("비밀번호 성공적으로 변경되었습니다.");
    }

    @GetMapping("/contact")
    public ResponseEntity<MemberResponseDto> getMemberInfo(
            @AuthenticationPrincipal MemberDetails memberDetails
    ) {
        MemberResponseDto memberInfo = accountService.getMemberInfo(memberDetails.getMember().getId());

        return ResponseEntity.ok(memberInfo);
    }

    @PatchMapping("/contact")
    public ResponseEntity<MemberResponseDto> updateMemberInfo(
            @Valid @RequestBody MemberUpdateRequestDto requestDto,
            @AuthenticationPrincipal MemberDetails memberDetails
    ) {
        MemberResponseDto updateMemberInfo = accountService.updateMemberInfo(memberDetails.getMember().getId(), requestDto);

        return ResponseEntity.ok(updateMemberInfo);
    }

}
