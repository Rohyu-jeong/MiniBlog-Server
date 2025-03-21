package com.rserver.miniblog.presentaion.controller;

import com.rserver.miniblog.application.dto.request.MemberUpdateRequest;
import com.rserver.miniblog.application.dto.request.PasswordUpdateRequest;
import com.rserver.miniblog.application.dto.request.SignUpRequest;
import com.rserver.miniblog.application.dto.response.ApiResponse;
import com.rserver.miniblog.application.dto.response.MemberResponse;
import com.rserver.miniblog.application.service.member.AccountCommandService;
import com.rserver.miniblog.application.service.member.AccountReadService;
import com.rserver.miniblog.infrastructure.security.MemberDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {

    private final AccountCommandService accountCommandService;
    private final AccountReadService accountReadService;

    @PostMapping
    public ApiResponse<Void> createMember(
            @Valid @RequestBody SignUpRequest requestDto
    ) {
        accountCommandService.register(requestDto);

        return ApiResponse.success();
    }

    @PatchMapping("/password")
    public ApiResponse<Void> updatePassword(
            @Valid @RequestBody PasswordUpdateRequest requestDto,
            @AuthenticationPrincipal MemberDetails memberDetails
    ) {
        accountCommandService.updatePassword(memberDetails.getMember().getId(), requestDto);

        return ApiResponse.success();
    }

    @GetMapping("/contact")
    public ApiResponse<MemberResponse> getMemberInfo(
            @AuthenticationPrincipal MemberDetails memberDetails
    ) {
        MemberResponse memberInfo = accountReadService.getMemberInfo(memberDetails.getMember().getId());

        return ApiResponse.success(memberInfo);
    }

    @PatchMapping("/contact")
    public ApiResponse<MemberResponse> updateMemberInfo(
            @Valid @RequestBody MemberUpdateRequest requestDto,
            @AuthenticationPrincipal MemberDetails memberDetails
    ) {
        MemberResponse updateMemberInfo = accountCommandService.updateMemberInfo(memberDetails.getMember().getId(), requestDto);

        return ApiResponse.success(updateMemberInfo);
    }

}
