package com.threed_model_market.project.contoller;

import com.threed_model_market.project.dto.UserDto;
import com.threed_model_market.project.exception_handler.exceptions.User.UserAlreadyExists;
import com.threed_model_market.project.exception_handler.exceptions.User.UserInvalidPasswordException;
import com.threed_model_market.project.exception_handler.exceptions.User.UserNotFoundMailException;
import com.threed_model_market.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDto UserDto) throws UserAlreadyExists {
        return ResponseEntity.ok(userService.registerUser(UserDto));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateUser(@RequestBody UserDto UserDto) throws UserNotFoundMailException, UserInvalidPasswordException {
        return ResponseEntity.ok(userService.authenticateUser(UserDto));
    }
}
