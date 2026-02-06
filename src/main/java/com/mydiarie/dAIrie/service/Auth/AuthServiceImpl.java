package com.mydiarie.dAIrie.service.Auth;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mydiarie.dAIrie.dto.authDTO.AuthResponseDTO;
import com.mydiarie.dAIrie.dto.authDTO.LoginRequestDTO;
import com.mydiarie.dAIrie.dto.authDTO.RegisterRequestDTO;
import com.mydiarie.dAIrie.models.DiarieUser;
import com.mydiarie.dAIrie.repository.DiarieUserRepository;
import com.mydiarie.dAIrie.security.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final DiarieUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public AuthResponseDTO register(RegisterRequestDTO dto) {

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email ya registrado");
        }

        DiarieUser user = new DiarieUser();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole("ROLE_USER"); // rol por defecto

        DiarieUser savedUser = userRepository.save(user);

        String token = jwtService.generateToken(savedUser.getEmail(), savedUser.getRole());

        return new AuthResponseDTO(
                token,
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getRole()
        );
    }

    @Override
    public AuthResponseDTO login(LoginRequestDTO dto) {

        DiarieUser user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Mail o contraseña incorrecto"));

        boolean passwordMatches = passwordEncoder.matches(dto.getPassword(), user.getPassword());
        if (!passwordMatches) throw new RuntimeException("Mail o contraseña incorrecto");

        String token = jwtService.generateToken(user.getEmail(), user.getRole());

        return new AuthResponseDTO(
                token,
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );
    }
}
