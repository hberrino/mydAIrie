package com.mydiarie.dAIrie.dto.DiarieUserDTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiarieUserResponseDTO {
    
    private Long id;
    private String name;
    private String email;
    private LocalDateTime createdAt;
}
