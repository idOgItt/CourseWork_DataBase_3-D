package com.threed_model_market.project.controller;

import com.threed_model_market.project.dto.UserImageDto;
import com.threed_model_market.project.model.UserImage;
import com.threed_model_market.project.service.UserImageService;
import com.threed_model_market.project.util.AccessValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user-images")
public class UserImageController {

    private final UserImageService userImageService;
    private final AccessValidator accessValidator;

    public UserImageController(UserImageService userImageService, AccessValidator accessValidator) {
        this.userImageService = userImageService;
        this.accessValidator = accessValidator;
    }

    @PreAuthorize("hasAuthority('UPLOAD_USER_IMAGES') or hasAuthority('ROLE_ADMIN')")
    @PostMapping("/upload")
    public ResponseEntity<UserImage> uploadUserImage(@RequestBody UserImageDto userImageDto, @RequestHeader("Authorization") String token) {
        accessValidator.validateUserAccess(token, userImageDto.getUserId());
        UserImage userImage = userImageService.uploadUserImage(userImageDto);
        return ResponseEntity.ok(userImage);
    }

    @PreAuthorize("hasAuthority('VIEW_USER_IMAGES') or hasAuthority('ROLE_ADMIN')")
    @GetMapping("/user/{userId}")
    public ResponseEntity<Optional<UserImage>> getUserImageByUserId(@PathVariable Long userId, @RequestHeader("Authorization") String token) {
        accessValidator.validateUserAccess(token, userId);
        return ResponseEntity.ok(userImageService.getUserImageByUserId(userId));
    }

    @PreAuthorize("hasAuthority('MANAGE_USER_IMAGES') or hasAuthority('ROLE_ADMIN')")
    @GetMapping("/filename/{filename}")
    public ResponseEntity<Optional<UserImage>> getUserImageByFilename(@PathVariable String filename, @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(userImageService.getUserImageByFilename(filename));
    }

    @PreAuthorize("hasAuthority('DELETE_USER_IMAGES') or hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserImage(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        Optional<UserImage> userImage = userImageService.getUserImageById(id);
        userImage.ifPresent(image -> accessValidator.validateUserAccess(token, image.getUser().getId()));
        userImageService.deleteUserImage(id);
        return ResponseEntity.noContent().build();
    }
}
