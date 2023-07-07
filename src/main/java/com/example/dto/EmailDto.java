package com.example.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class EmailDto {
    private Long id;
    @NotEmpty
    @Email
    private String emailName;
}
