package com.mydiarie.dAIrie.controller;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mydiarie.dAIrie.dto.DiarieUserDTO.ChangeUserPasswordDTO;
import com.mydiarie.dAIrie.dto.DiarieUserDTO.UpdateUserProfileRequestDTO;
import com.mydiarie.dAIrie.dto.DiarieUserDTO.UserProfileResponseDTO;
import com.mydiarie.dAIrie.service.DiarieUser.DiarieUserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final DiarieUserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserProfileResponseDTO> getProfile(
            Principal principal) {

        return ResponseEntity.ok(
                userService.getProfile(principal.getName()));
    }

    @PatchMapping("/me")
    public ResponseEntity<UserProfileResponseDTO> updateProfile(
            @Valid @RequestBody UpdateUserProfileRequestDTO dto,
            Principal principal) {

        return ResponseEntity.ok(
                userService.updateProfile(principal.getName(), dto));
    }

    @PatchMapping("/me/password")
    public ResponseEntity<Void> changePassword(
            @Valid @RequestBody ChangeUserPasswordDTO dto,
            Principal principal) {

        userService.changePassword(principal.getName(), dto);
        return ResponseEntity.noContent().build();
    }
}
