package com.threed_model_market.project.util;

import com.threed_model_market.project.exception_handler.exceptions.Security.UnauthorizedAccessException;
import com.threed_model_market.project.security.JwtTokenProvider;

public class AccessValidator {

    private final JwtTokenProvider jwtTokenProvider;

    public AccessValidator(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }


    public void validateUserAccess(String token, Long userId) {
        Long userIdFromToken = jwtTokenProvider.getUserIdFromJWT(token);

        boolean isAdmin = hasAdminAuthority(token);

        if (!userIdFromToken.equals(userId) && !isAdmin) {
            throw new UnauthorizedAccessException("Access denied.");
        }
    }

    private boolean hasAdminAuthority(String token) {
        return jwtTokenProvider.getAuthoritiesFromJWT(token).stream()
                .anyMatch(authority -> "ROLE_ADMIN".equals(authority.getAuthority()));
    }
}
