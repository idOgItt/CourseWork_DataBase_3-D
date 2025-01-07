package com.threed_model_market.project.service.impl;

import com.threed_model_market.project.config.PasswordConfig;
import com.threed_model_market.project.exception_handler.exceptions.User.UserNotFoundException;
import com.threed_model_market.project.model.Permission;
import com.threed_model_market.project.model.User;
import com.threed_model_market.project.repository.UserRepository;
import com.threed_model_market.project.service.AuthenticationService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final PasswordConfig bCryptPasswordEncoder;

    private final UserRepository userRepository;

    public AuthenticationServiceImpl(PasswordConfig bCryptPasswordEncoder, UserRepository userRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
    }

    public boolean authenticate(String rawPassword, String storedHashedPassword) {
        return bCryptPasswordEncoder.passwordEncoder().matches(rawPassword, storedHashedPassword);
    }

    @Override
    public void updateAuthenticationForUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (user.getRole() != null) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().getRolename()));

            for (Permission permission : user.getRole().getPermissions()) {
                authorities.add(new SimpleGrantedAuthority(permission.getPermissionName()));
            }
        } else {
            authorities.add(new SimpleGrantedAuthority("ROLE_GUEST"));
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                user.getUsername(),
                null,
                authorities
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}