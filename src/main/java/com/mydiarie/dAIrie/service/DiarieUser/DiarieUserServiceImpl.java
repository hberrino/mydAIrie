package com.mydiarie.dAIrie.service.DiarieUser;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mydiarie.dAIrie.dto.DiarieUserDTO.ChangeUserPasswordDTO;
import com.mydiarie.dAIrie.dto.DiarieUserDTO.UpdateUserProfileRequestDTO;
import com.mydiarie.dAIrie.dto.DiarieUserDTO.UserProfileResponseDTO;
import com.mydiarie.dAIrie.models.DiarieUser;
import com.mydiarie.dAIrie.repository.DiarieUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DiarieUserServiceImpl implements DiarieUserService {

    private final DiarieUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private UserProfileResponseDTO toDTO(DiarieUser user) {
        return new UserProfileResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt()
        );
    }

    private DiarieUser getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public UserProfileResponseDTO getProfile(String email) {

        DiarieUser user = getUserByEmail(email);
        return toDTO(user);
    }

    @Override
    public UserProfileResponseDTO updateProfile(
            String email,
            UpdateUserProfileRequestDTO dto) {

        DiarieUser user = getUserByEmail(email);

        if (dto.getName() != null) {
            user.setName(dto.getName());
        }

        DiarieUser updated = userRepository.save(user);

        return toDTO(updated);
    }

    @Override
public void changePassword(
        String email,
        ChangeUserPasswordDTO dto) {

    DiarieUser user = getUserByEmail(email);

    // validar contraseña actual
    if (!passwordEncoder.matches(
            dto.getCurrentPassword(),
            user.getPassword())) {

        throw new RuntimeException("Current password incorrect");
    }

    // setear nueva contraseña
    user.setPassword(
            passwordEncoder.encode(dto.getNewPassword())
    );

    userRepository.save(user);
}


    @Override
    public void deleteUser(String email) {

        DiarieUser user = getUserByEmail(email);

        userRepository.delete(user);
    }
}
