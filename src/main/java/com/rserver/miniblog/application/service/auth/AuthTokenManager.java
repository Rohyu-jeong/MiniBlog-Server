package com.rserver.miniblog.application.service.auth;

import com.rserver.miniblog.application.dto.internal.IssueTokenData;
import com.rserver.miniblog.application.dto.AuthToken;

public interface AuthTokenManager {

    AuthToken issueToken(IssueTokenData issueTokenData);

    IssueTokenData validateRefreshToken(String token);

    void revokeRefreshToken(String refreshToken);

}
