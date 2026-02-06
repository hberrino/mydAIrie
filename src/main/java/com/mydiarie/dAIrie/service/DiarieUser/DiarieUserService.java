package com.mydiarie.dAIrie.service.DiarieUser;

import com.mydiarie.dAIrie.dto.DiarieUserDTO.ChangeUserPasswordDTO;
import com.mydiarie.dAIrie.dto.DiarieUserDTO.UpdateUserProfileRequestDTO;
import com.mydiarie.dAIrie.dto.DiarieUserDTO.UserProfileResponseDTO;

public interface DiarieUserService {

    UserProfileResponseDTO getProfile(String email);

    UserProfileResponseDTO updateProfile(
            String email,
            UpdateUserProfileRequestDTO dto
    );

    void changePassword(
            String email,
            ChangeUserPasswordDTO dto
    );

    void deleteUser(String email);
}
