package com.example.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class PhoneDto {
    private Long id;
    @NotEmpty
    private String phoneNumber;
}
