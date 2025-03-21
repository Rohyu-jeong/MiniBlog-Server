package com.rserver.miniblog.domain.post;

import com.rserver.miniblog.common.BaseMessage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum PostErrorMessage implements BaseMessage {
    POST_NOT_FOUND(404, "게시글을 찾을 수 없습니다."),
    COMMENT_NOT_FOUND(404, "댓글을 찾을 수 없습니다."),
    NOT_POST_OWNER(401, "본인이 작성한 게시글만 수정할 수 있습니다."),
    NOT_COMMENT_OWNER(401, "본인의 댓글만 수정할 수 있습니다."),
    INVALID_COMMENT(400, "해당 게시글의 댓글이 아닙니다."),
    IMAGE_UPLOAD_FAILED(500, "이미지 업로드 실패"),
    INVALID_IMAGE_FORMAT(400, "잘못된 이미지 형식입니다."),
    IMAGE_NOT_FOUND(404, "이미지를 찾을 수 없습니다.");

    private final int status;
    private final String message;
}
