package com.rserver.miniblog.posttemp;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PostTempRequestDto {

    @NotBlank(message = "제목을 입력해야 합니다.")
    @Size(max = 100, message = "제목은 최대 100자까지 입력이 가능합니다.")
    private String title;

    @NotBlank(message = "내용을 입력해야 합니다.")
    private String content;

    private String image;

}
