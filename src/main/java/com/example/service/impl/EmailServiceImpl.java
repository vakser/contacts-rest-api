package com.example.service.impl;

import com.example.dto.EmailDto;
import com.example.entity.Contact;
import com.example.entity.Email;
import com.example.exception.ContactsApiException;
import com.example.exception.ResourceNotFoundException;
import com.example.repository.ContactRepository;
import com.example.repository.EmailRepository;
import com.example.service.EmailService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmailServiceImpl implements EmailService {
    private ContactRepository contactRepository;
    private EmailRepository emailRepository;
    private ModelMapper mapper;

    public EmailServiceImpl(ContactRepository contactRepository, EmailRepository emailRepository, ModelMapper mapper) {
        this.contactRepository = contactRepository;
        this.emailRepository = emailRepository;
        this.mapper = mapper;
    }

    @Override
    public EmailDto createEmail(long contactId, EmailDto emailDto) {
        Email email = mapToEntity(emailDto);
        Contact contact = contactRepository.findById(contactId).orElseThrow(ResourceNotFoundException::new);
        email.setContact(contact);
        Email newEmail = emailRepository.save(email);
        return mapToDto(newEmail);
    }

    @Override
    public List<EmailDto> getEmailsByContactId(long contactId) {
        List<Email> emails = emailRepository.findByContactId(contactId);
        return emails.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public EmailDto getEmailById(long contactId, long emailId) {
        return mapToDto(getEmailForContact(contactId, emailId));
    }

    @Override
    public EmailDto updateEmail(long contactId, long emailId, EmailDto emailDto) {
        Email email = getEmailForContact(contactId, emailId);
        email.setEmailName(emailDto.getEmailName());
        Email updatedEmail = emailRepository.save(email);
        return mapToDto(updatedEmail);
    }

    @Override
    public void deleteEmail(long contactId, long emailId) {
        emailRepository.delete(getEmailForContact(contactId, emailId));
    }

    private EmailDto mapToDto(Email email) {
        return mapper.map(email, EmailDto.class);
    }

    private Email mapToEntity(EmailDto emailDto) {
        return mapper.map(emailDto, Email.class);
    }

    private Email getEmailForContact(long contactId, long emailId) {
        Contact contact = contactRepository.findById(contactId).orElseThrow(ResourceNotFoundException::new);
        Email email = emailRepository.findById(emailId).orElseThrow(ResourceNotFoundException::new);
        if (!email.getContact().getId().equals(contact.getId())) {
            throw new ContactsApiException(HttpStatus.BAD_REQUEST, "Email does not belong to the contact");
        }
        return email;
    }
}
