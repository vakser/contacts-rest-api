package com.example.service.impl;

import com.example.dto.ContactDto;
import com.example.entity.Contact;
import com.example.entity.User;
import com.example.exception.ResourceNotFoundException;
import com.example.repository.ContactRepository;
import com.example.repository.UserRepository;
import com.example.service.ContactService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {
    private ContactRepository contactRepository;
    private UserRepository userRepository;
    private ModelMapper mapper;

    public ContactServiceImpl(ContactRepository contactRepository, UserRepository userRepository, ModelMapper mapper) {
        this.contactRepository = contactRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public ContactDto createContact(ContactDto contactDto) {
        User user = userRepository.findByEmail(contactDto.getUserEmail()).orElseThrow(ResourceNotFoundException::new);
        Contact contact = mapToEntity(contactDto);
        contact.setUser(user);
        Contact newContact = contactRepository.save(contact);
        return mapToDto(newContact);
    }

    @Override
    public List<ContactDto> findAllContacts() {
        List<Contact> contactList = contactRepository.findAll();
        return contactList.stream().map(this::mapToDto).toList();
    }

    @Override
    public List<ContactDto> findAllContactsByUserEmail(String email) {
        List<Contact> contactList = contactRepository.findAllByUserEmail(email);
        return contactList.stream().map(this::mapToDto).toList();
    }

    @Override
    public List<ContactDto> findAllContactsByUserId(Long id) {
        List<Contact> contactList = contactRepository.findAllByUserId(id);
        return contactList.stream().map(this::mapToDto).toList();
    }

    @Override
    public void deleteContactById(Long id) {
        Contact contact = contactRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        contactRepository.delete(contact);
    }

    @Override
    public ContactDto getContactById(Long id) {
        Contact contact = contactRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        return mapToDto(contact);
    }

    @Override
    public ContactDto updateContact(ContactDto contactDto, Long id) {
        Contact contact = contactRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        contact.setContactName(contactDto.getContactName());
        Contact updatedContact = contactRepository.save(contact);
        return mapToDto(updatedContact);
    }

    private ContactDto mapToDto(Contact contact) {
        return mapper.map(contact, ContactDto.class);
    }

    private Contact mapToEntity(ContactDto contactDto) {
        return mapper.map(contactDto, Contact.class);
    }
}
