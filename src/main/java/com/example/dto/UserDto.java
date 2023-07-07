package com.example.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Set;

@Data
public class UserDto {
    private Long id;
    @NotEmpty
    private String email;
    private Set<ContactDto> contacts;
}
