package com.ely.spring_community_library.dtos.UserDtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UpdateUserDto(
        @Size(min = 3, message = "O nome deve ter pelo menos 3 caracteres.") String name,
        @Email(message = "Formato do email inválido.") String email,
        boolean active) {
}
