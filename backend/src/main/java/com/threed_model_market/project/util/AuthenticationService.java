package com.threed_model_market.project.util;

import com.threed_model_market.project.model.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public interface AuthenticationService {

    boolean authenticate(String rawPassword, String storedHashedPassword);

    void updateAuthenticationForUser(Long id);

    void authenticateAndAssignRole(User user, String rawPassword);

    void updateAuthenticationAfterRoleChange(User user);

    List<GrantedAuthority> getAuthoritiesForUser(User user);
}
