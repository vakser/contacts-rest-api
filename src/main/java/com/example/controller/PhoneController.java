package com.example.controller;

import com.example.dto.EmailDto;
import com.example.dto.PhoneDto;
import com.example.service.PhoneService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class PhoneController {
    private PhoneService phoneService;

    public PhoneController(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @PostMapping("/{contactId}/phones")
    public ResponseEntity<PhoneDto> createPhone(@PathVariable(value = "contactId") long contactId,
                                                @Valid @RequestBody PhoneDto phoneDto) {
        return new ResponseEntity<>(phoneService.createPhone(contactId, phoneDto), HttpStatus.CREATED);
    }

    @GetMapping("/{contactId}/phones")
    public List<PhoneDto> getPhonesByContactId(@PathVariable(value = "contactId") long contactId) {
        return phoneService.getPhonesByContactId(contactId);
    }

    @GetMapping("/{contactId}/phones/{id}")
    public ResponseEntity<PhoneDto> getPhoneById(@PathVariable(value = "contactId") long contactId,
                                                 @PathVariable(value = "id") long phoneId) {
        PhoneDto phoneDto = phoneService.getPhoneById(contactId, phoneId);
        return new ResponseEntity<>(phoneDto, HttpStatus.OK);
    }

    @PutMapping("/{contactId}/phones/{id}")
    public ResponseEntity<PhoneDto> updateEmail(@PathVariable(value = "contactId") long contactId,
                                                @PathVariable(value = "id") long phoneId,
                                                @Valid @RequestBody PhoneDto phoneDto) {
        PhoneDto updatedPhone = phoneService.updatePhone(contactId, phoneId, phoneDto);
        return new ResponseEntity<>(updatedPhone, HttpStatus.OK);
    }

    @DeleteMapping("/{contactId}/phones/{id}")
    public ResponseEntity<String> deletePhone(@PathVariable(value = "contactId") long contactId, @PathVariable(value = "id") long phoneId) {
        phoneService.deletePhone(contactId, phoneId);
        return new ResponseEntity<>("Phone deleted successfully", HttpStatus.OK);
    }
}
