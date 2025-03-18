package com.rserver.miniblog.presentaion.controller;

import com.rserver.miniblog.application.dto.request.MemberUpdateRequestDto;
import com.rserver.miniblog.application.dto.request.PasswordUpdateRequestDto;
import com.rserver.miniblog.application.dto.request.SignUpRequest;
import com.rserver.miniblog.application.dto.response.ApiResponse;
import com.rserver.miniblog.application.dto.response.MemberResponseDto;
import com.rserver.miniblog.application.service.member.AccountService;
import com.rserver.miniblog.infrastructure.security.MemberDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    public ApiResponse<Void> createMember(
            @Valid @RequestBody SignUpRequest requestDto
    ) {
        accountService.register(requestDto);

        return ApiResponse.success();
    }

    @PatchMapping("/password")
    public ApiResponse<Void> updatePassword(
            @Valid @RequestBody PasswordUpdateRequestDto requestDto,
            @AuthenticationPrincipal MemberDetails memberDetails
    ) {
        accountService.updatePassword(memberDetails.getMember().getId(), requestDto);

        return ApiResponse.success();
    }

    @GetMapping("/contact")
    public ApiResponse<MemberResponseDto> getMemberInfo(
            @AuthenticationPrincipal MemberDetails memberDetails
    ) {
        MemberResponseDto memberInfo = accountService.getMemberInfo(memberDetails.getMember().getId());

        return ApiResponse.success(memberInfo);
    }

    @PatchMapping("/contact")
    public ApiResponse<MemberResponseDto> updateMemberInfo(
            @Valid @RequestBody MemberUpdateRequestDto requestDto,
            @AuthenticationPrincipal MemberDetails memberDetails
    ) {
        MemberResponseDto updateMemberInfo = accountService.updateMemberInfo(memberDetails.getMember().getId(), requestDto);

        return ApiResponse.success(updateMemberInfo);
    }

}
