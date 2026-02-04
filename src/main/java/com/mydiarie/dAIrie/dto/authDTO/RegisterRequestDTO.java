package com.mydiarie.dAIrie.dto.authDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDTO {
    
    @NotBlank
    @Size(min = 2, max = 50)
    private String name;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    @Size(min = 6, max = 100)
    private String password;

    
}
