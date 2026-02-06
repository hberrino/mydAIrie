package com.mydiarie.dAIrie.service.Auth;

import com.mydiarie.dAIrie.dto.authDTO.AuthResponseDTO;
import com.mydiarie.dAIrie.dto.authDTO.LoginRequestDTO;
import com.mydiarie.dAIrie.dto.authDTO.RegisterRequestDTO;

public interface AuthService {
    
    AuthResponseDTO register(RegisterRequestDTO dto);
    AuthResponseDTO login(LoginRequestDTO dto);
    
}
