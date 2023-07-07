package com.example.service;

import com.example.dto.EmailDto;

import java.util.List;

public interface EmailService {
    EmailDto createEmail(long contactId, EmailDto emailDto);

    List<EmailDto> getEmailsByContactId(long contactId);

    EmailDto getEmailById(long contactId, long emailId);

    EmailDto updateEmail(long contactId, long emailId, EmailDto emailDto);

    void deleteEmail(long contactId, long emailId);
}
