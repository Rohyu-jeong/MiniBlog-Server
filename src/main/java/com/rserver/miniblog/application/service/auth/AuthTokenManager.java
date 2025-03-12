package com.rserver.miniblog.application.service.auth;

import com.rserver.miniblog.application.dto.internal.IssueTokenInfo;
import com.rserver.miniblog.application.dto.response.TokenResponseDto;

public interface AuthTokenManager {

    TokenResponseDto issueToken (IssueTokenInfo issueTokenInfo);

    IssueTokenInfo validateRefreshToken (String token);

    void revokeRefreshToken (String refreshToken);

}
