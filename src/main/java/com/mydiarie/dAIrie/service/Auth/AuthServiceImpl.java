package com.mydiarie.dAIrie.service.Auth;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mydiarie.dAIrie.dto.authDTO.AuthResponseDTO;
import com.mydiarie.dAIrie.dto.authDTO.LoginRequestDTO;
import com.mydiarie.dAIrie.dto.authDTO.RegisterRequestDTO;
import com.mydiarie.dAIrie.models.DiarieUser;
import com.mydiarie.dAIrie.repository.DiarieUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class AuthServiceImpl implements AuthService {

    private static final String TOKEN_PLACEHOLDER = null;      
    private final DiarieUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
public AuthResponseDTO register(RegisterRequestDTO dto) {

    if (userRepository.existsByEmail(dto.getEmail())) {
        throw new RuntimeException("Email ya registrado");
    }

    DiarieUser user = new DiarieUser();
    user.setName(dto.getName());
    user.setEmail(dto.getEmail());
    user.setPassword(passwordEncoder.encode(dto.getPassword()));

    DiarieUser savedUser = userRepository.save(user);

    return new AuthResponseDTO(
    TOKEN_PLACEHOLDER,
    savedUser.getId(),
    savedUser.getName(),
    savedUser.getEmail()
    );
}

    @Override
public AuthResponseDTO login(LoginRequestDTO dto) {

    DiarieUser user = userRepository.findByEmail(dto.getEmail())
            .orElseThrow(() -> new RuntimeException("Email no encontrado"));


    boolean passwordMatches = passwordEncoder.matches(
            dto.getPassword(),
            user.getPassword()
    );

    if (!passwordMatches) {
        throw new RuntimeException("Email o contraseña incorrectos");
    }

    return new AuthResponseDTO(
            TOKEN_PLACEHOLDER, // token todavía no implementado
            user.getId(),
            user.getName(),
            user.getEmail()
    );
}

    
}
