package com.ely.spring_community_library.dtos.UserDtos;

import com.ely.spring_community_library.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateUserDto(
        @NotBlank(message = "O nome é obrigatório")
        @Size(min = 3, message = "O nome deve ter pelo menos 3 caracteres.")
        String name,
        @NotBlank(message = "O email é obrigatório")
        @Email(message = "Formato do email inválido")
        String email,
        @NotBlank(message = "A senha é obrigatória")
        @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres.")
        String password,
        @NotNull(message = "O cargo do usuário é obrigatório")
        UserRole role,
        boolean active
    ) {
}
