package com.example.service;

import com.example.dto.ContactDto;

import java.util.List;

public interface ContactService {
    ContactDto createContact(ContactDto contactDto);

    List<ContactDto> findAllContacts();

    List<ContactDto> findAllContactsByUserEmail(String email);

    List<ContactDto> findAllContactsByUserId(Long id);

    void deleteContactById(Long id);

    ContactDto getContactById(Long id);

    ContactDto updateContact(ContactDto contactDto, Long id);

}
