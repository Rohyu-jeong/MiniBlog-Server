package com.rserver.miniblog.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CommentRequestDto {

    @Positive(message = "유효한 게시글 ID여야 합니다.")
    private Long postId;

    @NotBlank(message = "내용을 입력해야 합니다.")
    private String Content;

}
