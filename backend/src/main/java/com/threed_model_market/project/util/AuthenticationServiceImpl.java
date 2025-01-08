package com.threed_model_market.project.util;

import com.threed_model_market.project.config.PasswordConfig;
import com.threed_model_market.project.exception_handler.exceptions.Role.RoleNotFoundException;
import com.threed_model_market.project.exception_handler.exceptions.User.UserInvalidPasswordException;
import com.threed_model_market.project.exception_handler.exceptions.User.UserNotFoundException;
import com.threed_model_market.project.model.Permission;
import com.threed_model_market.project.model.Role;
import com.threed_model_market.project.model.User;
import com.threed_model_market.project.repository.RoleRepository;
import com.threed_model_market.project.repository.UserRepository;
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
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public AuthenticationServiceImpl(PasswordConfig bCryptPasswordEncoder, RoleRepository roleRepository, UserRepository userRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public boolean authenticate(String rawPassword, String storedHashedPassword) {
        return bCryptPasswordEncoder.passwordEncoder().matches(rawPassword, storedHashedPassword);
    }

    @Override
    public void updateAuthenticationForUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        updateAuthenticationAfterRoleChange(user);
    }

    @Override
    public void authenticateAndAssignRole(User user, String rawPassword) {
        if (authenticate(rawPassword, user.getPasswordHash())) {
            assignClientRoleAfterAuthentication(user);
        } else {
            throw new UserInvalidPasswordException("Authentication failed");
        }
    }

    public void assignClientRoleAfterAuthentication(User user) {
        Role clientRole = roleRepository.findByRolename("CLIENT")
                .orElseThrow(() -> new RoleNotFoundException("Role CLIENT not found"));
        if (!user.getRole().equals(clientRole)) {
            user.setRole(clientRole);
            userRepository.save(user);
        }
    }

    @Override
    public void updateAuthenticationAfterRoleChange(User user) {
        List<GrantedAuthority> authorities = getAuthoritiesForUser(user);

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                user.getUsername(),
                null,
                authorities
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    public List<GrantedAuthority> getAuthoritiesForUser(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        if (user.getRole() != null) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().getRolename()));
            for (Permission permission : user.getRole().getPermissions()) {
                authorities.add(new SimpleGrantedAuthority(permission.getPermissionName()));
            }
        } else {
            Role guestRole = roleRepository.findByRolename("GUEST")
                    .orElseThrow(() -> new RoleNotFoundException("Role CLIENT not found"));
            authorities.add(new SimpleGrantedAuthority("ROLE_" + guestRole.getRolename()));
            for (Permission permission : guestRole.getPermissions()) {
                authorities.add(new SimpleGrantedAuthority(permission.getPermissionName()));
            }
        }
        return authorities;
    }
}