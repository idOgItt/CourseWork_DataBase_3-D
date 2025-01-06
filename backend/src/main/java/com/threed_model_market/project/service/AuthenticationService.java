package com.threed_model_market.project.service;

public interface AuthenticationService {

    public boolean authenticate(String rawPassword, String storedHashedPassword);
}
