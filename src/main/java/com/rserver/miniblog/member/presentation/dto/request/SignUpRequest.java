package com.rserver.miniblog.member.presentation.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SignUpRequest {

    @NotBlank(message = "아이디는 공백일 수 없습니다.")
    @Size(min = 3, max = 20, message = "아이디는 3자 이상 20자 이하로 입력해야 합니다.")
    private String username;

    @NotBlank(message = "비밀번호는 공백일 수 없습니다.")
    @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하로 입력해야 합니다.")
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$",
            message = "비밀번호는 최소 하나의 영문자, 하나의 숫자, 하나의 특수문자(@$!%*?&)를 포함해야 합니다."
    )
    private String password;

    @NotBlank(message = "닉네임은 공백일 수 없습니다.")
    @Size(min = 2, max = 10, message = "닉네임은 2자 이상 10자 이하로 입력해야 합니다.")
    @Pattern(
            regexp = "^[가-힣a-zA-Z0-9]+$",
            message = "닉네임은 한글, 영어, 숫자만 포함할 수 있습니다."
    )
    private String nickname;

    @NotBlank(message = "이메일은 공백일 수 없습니다.")
    @Email(message = "유효한 이메일 형식이어야 합니다.")
    @Size(max = 100, message = "이메일은 100자 이하로 입력해야 합니다.")
    private String email;

    @NotBlank(message = "전화번호는 공백일 수 없습니다.")
    @Size(min = 13, max = 13, message = "전화번호는 11자로 입력해야 합니다.")
    @Pattern(
            regexp = "^010-\\d{4}-\\d{4}$",
            message = "전화번호는 010-XXXX-XXXX 형식이어야 합니다."
    )
    private String phoneNumber;

}
