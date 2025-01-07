package com.threed_model_market.project.controller;

import com.threed_model_market.project.dto.ImageDto;
import com.threed_model_market.project.exception_handler.exceptions.Image.ImageNotFoundException;
import com.threed_model_market.project.model.Image;
import com.threed_model_market.project.security.JwtTokenProvider;
import com.threed_model_market.project.service.ImageService;
import com.threed_model_market.project.util.AccessValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    private final ImageService imageService;
    private final JwtTokenProvider jwtTokenProvider;
    private final AccessValidator accessValidator;

    public ImageController(ImageService imageService, JwtTokenProvider jwtTokenProvider, AccessValidator accessValidator) {
        this.imageService = imageService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.accessValidator = accessValidator;
    }

    @PreAuthorize("hasAuthority('UPLOAD_IMAGES') or hasAuthority('ROLE_ADMIN')")
    @PostMapping("/upload")
    public ResponseEntity<Image> uploadImage(@RequestBody ImageDto imageDto, @RequestHeader("Authorization") String token) {
        Long userIdFromToken = jwtTokenProvider.getUserIdFromJWT(token);

        accessValidator.validateUserAccess(token, userIdFromToken);

        Image image = imageService.uploadImage(imageDto);
        return ResponseEntity.ok(image);
    }

    @PreAuthorize("hasAuthority('MANAGE_IMAGES') or hasAuthority('ROLE_ADMIN')")
    @GetMapping("/model/{modelId}/filename/{filename}")
    public ResponseEntity<Image> getImageByModelIdAndFilename(@PathVariable Long modelId, @PathVariable String filename, @RequestHeader("Authorization") String token) {
        Optional<Image> image = imageService.getImageByModelIdAndFilename(modelId, filename);
        return image.map(ResponseEntity::ok).orElseThrow(() -> new ImageNotFoundException("Image not found for model ID " + modelId + " and filename " + filename));
    }

    @PreAuthorize("hasAuthority('MANAGE_IMAGES') or hasAuthority('ROLE_ADMIN')")
    @GetMapping("/filename/{filename}")
    public ResponseEntity<Image> getImageByFilename(@PathVariable String filename, @RequestHeader("Authorization") String token) {
        Optional<Image> image = imageService.getImageByFilename(filename);
        return image.map(ResponseEntity::ok).orElseThrow(() -> new ImageNotFoundException("Image not found with filename " + filename));
    }

    @PreAuthorize("hasAuthority('DELETE_IMAGES') or hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteImage(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        Optional<Image> image = imageService.getImageById(id);

        if (image.isPresent()) {
            Long ownerId = image.get().getModel().getUser().getId();

            accessValidator.validateUserAccess(token, ownerId);
        } else {
            throw new ImageNotFoundException("Image not found with ID: " + id);
        }

        imageService.deleteImage(id);
        return ResponseEntity.noContent().build();
    }
}
