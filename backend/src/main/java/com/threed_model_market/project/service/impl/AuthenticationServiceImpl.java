package com.threed_model_market.project.service.impl;

import com.threed_model_market.project.service.AuthenticationService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthenticationServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public boolean authenticate(String rawPassword, String storedHashedPassword) {
        return bCryptPasswordEncoder.matches(rawPassword, storedHashedPassword);
    }
}