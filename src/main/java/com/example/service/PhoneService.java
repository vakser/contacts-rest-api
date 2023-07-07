package com.example.service;

import com.example.dto.PhoneDto;

import java.util.List;

public interface PhoneService {
    PhoneDto createPhone(long contactId, PhoneDto phoneDto);

    List<PhoneDto> getPhonesByContactId(long contactId);

    PhoneDto getPhoneById(long contactId, long phoneId);

    PhoneDto updatePhone(long contactId, long phoneId, PhoneDto phoneDto);

    void deletePhone(long contactId, long phoneId);
}
