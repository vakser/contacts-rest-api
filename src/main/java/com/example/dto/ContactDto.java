package com.example.dto;

import com.example.entity.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class ContactDto {
    private Long id;
    @NotEmpty
    @Size(min = 2, message = "Contact name should be at least 2 characters long")
    private String contactName;
    private Set<EmailDto> emails;
    private Set<PhoneDto> phones;
    private String userEmail;
}
