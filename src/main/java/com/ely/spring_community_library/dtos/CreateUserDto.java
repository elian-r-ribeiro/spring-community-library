package com.ely.spring_community_library.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserDto(
        @NotBlank(message = "O nome é obrigatório")
        @Size(min = 3, message = "O nome deve ter pelo menos 3 caracteres.")
        String name,
        @NotBlank(message = "O email é obrigatório")
        @Email(message = "Formato do email inválido")
        String email,
        boolean active) {
}
