package com.example.service.impl;

import com.example.dto.EmailDto;
import com.example.dto.PhoneDto;
import com.example.entity.Contact;
import com.example.entity.Email;
import com.example.entity.Phone;
import com.example.exception.ContactsApiException;
import com.example.exception.ResourceNotFoundException;
import com.example.repository.ContactRepository;
import com.example.repository.PhoneRepository;
import com.example.service.PhoneService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhoneServiceImpl implements PhoneService {
    private ContactRepository contactRepository;
    private PhoneRepository phoneRepository;
    private ModelMapper mapper;

    public PhoneServiceImpl(ContactRepository contactRepository, PhoneRepository phoneRepository, ModelMapper mapper) {
        this.contactRepository = contactRepository;
        this.phoneRepository = phoneRepository;
        this.mapper = mapper;
    }

    @Override
    public PhoneDto createPhone(long contactId, PhoneDto phoneDto) {
        Phone phone = mapToEntity(phoneDto);
        Contact contact = contactRepository.findById(contactId).orElseThrow(ResourceNotFoundException::new);
        phone.setContact(contact);
        Phone newPhone = phoneRepository.save(phone);
        return mapToDto(newPhone);
    }

    @Override
    public List<PhoneDto> getPhonesByContactId(long contactId) {
        List<Phone> phones = phoneRepository.findByContactId(contactId);
        return phones.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public PhoneDto getPhoneById(long contactId, long phoneId) {
        return mapToDto(getPhoneForContact(contactId, phoneId));
    }

    @Override
    public PhoneDto updatePhone(long contactId, long phoneId, PhoneDto phoneDto) {
        Phone phone = getPhoneForContact(contactId, phoneId);
        phone.setPhoneNumber(phoneDto.getPhoneNumber());
        Phone updatedPhone = phoneRepository.save(phone);
        return mapToDto(updatedPhone);
    }

    @Override
    public void deletePhone(long contactId, long phoneId) {
        phoneRepository.delete(getPhoneForContact(contactId, phoneId));
    }

    private PhoneDto mapToDto(Phone phone) {
        return mapper.map(phone, PhoneDto.class);
    }

    private Phone mapToEntity(PhoneDto phoneDto) {
        return mapper.map(phoneDto, Phone.class);
    }

    private Phone getPhoneForContact(long contactId, long phoneId) {
        Contact contact = contactRepository.findById(contactId).orElseThrow(ResourceNotFoundException::new);
        Phone phone = phoneRepository.findById(phoneId).orElseThrow(ResourceNotFoundException::new);
        if (!phone.getContact().getId().equals(contact.getId())) {
            throw new ContactsApiException(HttpStatus.BAD_REQUEST, "Phone does not belong to the contact");
        }
        return phone;
    }
}
