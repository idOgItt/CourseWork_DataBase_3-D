package com.threed_model_market.project.service;

public interface AuthenticationService {

    boolean authenticate(String rawPassword, String storedHashedPassword);

    void updateAuthenticationForUser(Long id);
}
