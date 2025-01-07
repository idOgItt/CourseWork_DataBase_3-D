package com.threed_model_market.project.service.impl;

import com.threed_model_market.project.config.PasswordConfig;
import com.threed_model_market.project.dto.RoleDto;
import com.threed_model_market.project.dto.UserDto;
import com.threed_model_market.project.exception_handler.exceptions.Role.RoleNotFoundException;
import com.threed_model_market.project.exception_handler.exceptions.User.*;
import com.threed_model_market.project.model.Role;
import com.threed_model_market.project.model.User;

import com.threed_model_market.project.repository.RoleRepository;
import com.threed_model_market.project.repository.UserRepository;
import com.threed_model_market.project.security.CustomUserDetails;
import com.threed_model_market.project.security.CustomUserDetailsService;
import com.threed_model_market.project.security.JwtTokenProvider;
import com.threed_model_market.project.service.AuthenticationService;
import com.threed_model_market.project.service.UserService;
import jakarta.validation.constraints.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.threed_model_market.project.util.ValidationUtils.isValidEmail;

@Service
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordConfig passwordEncoder;
    private final AuthenticationService authenticationService;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final JwtTokenProvider jwtTokenProvider;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordConfig passwordEncoder, AuthenticationService authenticationService, AuthenticationManager authenticationManager, CustomUserDetailsService userDetailsService, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationService = authenticationService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public ResponseEntity<String> registerUser(UserDto userDto) throws UserAlreadyExistsException, UserInvalidMailFormatException {
        if (!isValidEmail(userDto.getEmail())) {
            throw new UserInvalidMailFormatException("Mail pattern is invalid");
        }

        if (isEmailAlreadyExist(userDto.getEmail())) {
            throw new UserAlreadyExistsException("User already exists");
        }

        String hashedPassword = passwordEncoder.passwordEncoder().encode(userDto.getPassword());

        Role role = roleRepository.findByRolename(userDto.getRole().getRoleName())
                .orElseThrow(() -> new RoleNotFoundException("Role not found"));

        User user = new User(userDto.getEmail(), userDto.getUsername(), hashedPassword, role);

        saveUser(user);
        logger.info("User with email {} registered successfully", userDto.getEmail());

        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }

    @Override
    public String authenticateUser(UserDto userDto) throws UserInvalidPasswordException, UserNotFoundMailException, UserInvalidMailFormatException {
        logger.info("Authenticating user with email: {}", userDto.getEmail());

        Optional<User> userOptional = userRepository.findByEmail(userDto.getEmail());
        User user = userOptional.orElseThrow(() -> new UserNotFoundMailException("User not found"));

        if (!authenticationService.authenticate(userDto.getPassword(), user.getPasswordhash())) {
            throw new UserInvalidPasswordException("Invalid password");
        }

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword());

        authenticationManager.authenticate(authenticationToken);

        CustomUserDetails userDetails = userDetailsService.loadUserByUsername(userDto.getEmail());

        logger.info("User with email {} authenticated successfully", userDto.getEmail());
        return jwtTokenProvider.generateToken(userDetails.getUsername(), userDetails.getUserId(), userDetails.getAuthorities());
    }

    @Override
    public User saveUser(User user) {
        logger.info("Saving user with email: {}", user.getEmail());
        return userRepository.save(user);
    }

    @Override
    public User saveUserAndFlush(User user) {
        logger.info("Saving and Flushing user with email: {}", user.getEmail());
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User updateUser(Long id, UserDto dto) throws UserNotFoundException, UserInvalidMailFormatException {
        logger.info("Attempting to update user with id: {}", id);

        if (!isValidEmail(dto.getEmail())){
            throw new UserInvalidMailFormatException("Mail pattern is invalid");
        }

        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());
        user.setPasswordhash(passwordEncoder.passwordEncoder().encode(dto.getPassword()));

        Role newRole = roleRepository.findByRolename(dto.getRole().getRoleName())
                .orElseThrow(() -> new RoleNotFoundException("Role not found"));

        user.setRole(newRole);

        authenticationService.updateAuthenticationForUser(user.getId());

        logger.info("User with id {} updated successfully", id);

        return saveUser(user);
    }

    @Override
    public void removeUserById(Long id) throws UserNotFoundException {
        logger.info("Attempting to remove user with id: {}", id);

        User user = getUserById(id);
        this.userRepository.delete(user);

        logger.info("User with id {} removed successfully", id);
    }

    @Override
    public List<User> getAllUser() {
        logger.info("Fetching all users");
        return this.userRepository.findAll();
    }

    @Override
    public User getUserByEmail(@Email String email) throws UserNotFoundMailException, UserInvalidMailFormatException {
        logger.info("Fetching user with email: {}", email);

        if (!isValidEmail(email)){
            throw new UserInvalidMailFormatException("Mail pattern is invalid");
        }

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundMailException("User not found"));
    }


    @Override
    public User getUserById(Long id) throws UserNotFoundException {
        logger.info("Fetching user with id: {}", id);
        return this.userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Override
    public boolean isEmailAlreadyExist(@Email String email) throws UserInvalidMailFormatException {
        logger.info("Checking if email {} already exists", email);

        if (!isValidEmail(email)){
            throw new UserInvalidMailFormatException("Mail pattern is invalid");
        }

        return this.userRepository.findByEmail(email).isPresent();
    }

    @Override
    public User setUserRole(Long id, RoleDto dto) throws UserNotFoundException {
        logger.info("Adding role to user with id: {}", id);

        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Role role = roleRepository.findByRolename(dto.getRoleName())
                .orElseThrow(() -> new RoleNotFoundException("Role not found"));

        if (user.getRole().equals(role)) {
            logger.info("User with id {} already has the role {}", id, dto.getRoleName());
            return user;
        }

        user.setRole(role);

        userRepository.saveAndFlush(user);

        authenticationService.updateAuthenticationForUser(user.getId());

        logger.info("Role added to user with id: {}", id);
        return user;
    }

    public void removeRoleFromUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        Role guestRole = roleRepository.findByRolename("GUEST")
                .orElseThrow(() -> new RoleNotFoundException("Role GUEST not found"));
        user.setRole(guestRole);

        authenticationService.updateAuthenticationForUser(user.getId());
    }

    public List<Role> getRolesByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        return List.of(user.getRole());
    }

    public void changeUserPassword(Long id, String newPassword){
        User user = getUserById(id);
        String hashedPassword = passwordEncoder.passwordEncoder().encode(newPassword);
        user.setPasswordhash(hashedPassword);
        saveUser(user);
    }

    public void changeUserEmail(Long id, String newEmail) throws UserInvalidMailFormatException, UserAlreadyExistsException {

        if (!isValidEmail(newEmail)) {
            throw new UserInvalidMailFormatException("Invalid email format");
        }

        if (isEmailAlreadyExist(newEmail)) {
            throw new UserAlreadyExistsException("Email already exists");
        }

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        user.setEmail(newEmail);
        saveUser(user);

        logger.info("Email for user with id {} changed to {}", id, newEmail);

    }

    public void changeUserName(Long id, String newName){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        user.setUsername(newName);
        saveUser(user);

        logger.info("Username for user with id {} changed to {}", id, newName);
    }
}
