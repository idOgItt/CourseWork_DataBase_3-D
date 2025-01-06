package com.threed_model_market.project.service.impl;

import com.threed_model_market.project.config.PasswordConfig;
import com.threed_model_market.project.dto.RoleDto;
import com.threed_model_market.project.dto.UserDto;
import com.threed_model_market.project.enums.PrivilegeEnum;
import com.threed_model_market.project.exception_handler.exceptions.User.UserNotFoundMailException;
import com.threed_model_market.project.exception_handler.exceptions.User.UserAlreadyExists;
import com.threed_model_market.project.exception_handler.exceptions.User.UserInvalidPasswordException;
import com.threed_model_market.project.exception_handler.exceptions.User.UserNotFoundException;
import com.threed_model_market.project.model.Privilege;
import com.threed_model_market.project.model.Role;
import com.threed_model_market.project.model.User;
import com.threed_model_market.project.repository.RoleRepository;
import com.threed_model_market.project.repository.UserRepository;
import com.threed_model_market.project.security.JwtTokenProvider;
import com.threed_model_market.project.service.AuthenticationService;
import com.threed_model_market.project.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserServiceImp implements UserService {

    private final Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    public void UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    private AuthenticationService passwordAuthentication;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private PasswordConfig passwordConfig;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public ResponseEntity<String> registerUser(UserDto userDto) throws UserAlreadyExists {
        if (isEmailAlreadyExist(userDto.getEmail())) {
            throw new UserAlreadyExists("User already exists");
        }

        String hashedPassword = passwordConfig.passwordEncoder().encode(userDto.getPassword());
        Role role = roleRepository.findByType(userDto.getRole().getType());
        User user = new User(userDto.getEmail(), userDto.getUsername(), hashedPassword, role);

        userRepository.save(user);
        logger.info("User with email {} registered successfully", userDto.getEmail());

        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }

    @Override
    public String authenticateUser(UserDto userDto) throws UserInvalidPasswordException, UserNotFoundMailException {
        logger.info("Authenticating user with email: {}", userDto.getEmail());

        Optional<User> userOptional = userRepository.findByEmail(userDto.getEmail());
        User user = userOptional.orElseThrow(() -> new UserNotFoundMailException("User not found"));

        if (!passwordAuthentication.authenticate(userDto.getPassword(), user.getPassword())) {
            throw new UserInvalidPasswordException("Invalid password");
        }

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword());

        authenticationManager.authenticate(authenticationToken);

        UserDetails userDetails = userDetailsService.loadUserByUsername(userDto.getEmail());

        logger.info("User with email {} authenticated successfully", userDto.getEmail());
        return jwtTokenProvider.generateToken(userDetails.getUsername());
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
    public User updateUser(Long id, UserDto dto) throws UserNotFoundException {
        logger.info("Attempting to update user with id: {}", id);

        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        user.setEmail(dto.getEmail());
        user.setUserName(dto.getUsername());
        user.setPassword(passwordConfig.passwordEncoder().encode(dto.getPassword()));
        user.setRole(dto.getRole());
        logger.info("User with id {} updated successfully", id);

        return this.userRepository.save(user);
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
    public User getUserByEmail(String email) throws UserNotFoundMailException {
        logger.info("Fetching user with email: {}", email);
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
    public boolean isEmailAlreadyExist(String email) {
        logger.info("Checking if email {} already exists", email);
        return this.userRepository.findByEmail(email).isPresent();
    }

    @Override
    public User addUserRole(Long id, RoleDto dto) throws UserNotFoundException {
        logger.info("Adding role to user with id: {}", id);
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
        List<Privilege> privileges = new ArrayList<>();
        for (PrivilegeEnum p : dto.getPrivilegeEnums()) {
            privileges.add(new Privilege(p));
        }
        Role role = new Role();
        role.setType(dto.getRoleEnum());
        role.setPrivileges(privileges);
        user.setRole(role);

        userRepository.saveAndFlush(user);

        logger.info("Role added to user with id: {}", id);
        return user;
    }
}
