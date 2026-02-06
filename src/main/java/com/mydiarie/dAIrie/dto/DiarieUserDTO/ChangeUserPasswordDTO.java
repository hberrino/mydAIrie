package com.mydiarie.dAIrie.dto.DiarieUserDTO;

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
public class ChangeUserPasswordDTO {

    @NotBlank
    private String currentPassword;

    @NotBlank
    @Size(min = 6, max = 100)
    private String newPassword;
}
