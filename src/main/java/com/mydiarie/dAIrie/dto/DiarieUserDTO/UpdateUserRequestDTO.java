package com.mydiarie.dAIrie.dto.DiarieUserDTO;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor@NoArgsConstructor
public class UpdateUserRequestDTO {

    @Size(min = 2, max = 50)
    private String name;
    @Size(min = 5, max = 18)
    private String newPassword;
    
}
