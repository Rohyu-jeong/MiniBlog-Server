package com.rserver.miniblog.application.service.auth;

import com.rserver.miniblog.application.dto.internal.IssueTokenInfo;
import com.rserver.miniblog.application.dto.AuthToken;

public interface AuthTokenManager {

    AuthToken issueToken(IssueTokenInfo issueTokenInfo);

    IssueTokenInfo validateRefreshToken(String token);

    void revokeRefreshToken(String refreshToken);

}
