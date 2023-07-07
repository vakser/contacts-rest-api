package com.example.controller;

import com.example.dto.EmailDto;
import com.example.service.EmailService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class EmailController {
    private EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/{contactId}/emails")
    public ResponseEntity<EmailDto> createEmail(@PathVariable(value = "contactId") long contactId,
                                                  @Valid @RequestBody EmailDto emailDto) {
        return new ResponseEntity<>(emailService.createEmail(contactId, emailDto), HttpStatus.CREATED);
    }

    @GetMapping("/{contactId}/emails")
    public List<EmailDto> getEmailsByContactId(@PathVariable(value = "contactId") long contactId) {
        return emailService.getEmailsByContactId(contactId);
    }

    @GetMapping("/{contactId}/emails/{id}")
    public ResponseEntity<EmailDto> getEmailById(@PathVariable(value = "contactId") long contactId,
                                                     @PathVariable(value = "id") long emailId) {
        EmailDto emailDto = emailService.getEmailById(contactId, emailId);
        return new ResponseEntity<>(emailDto, HttpStatus.OK);
    }

    @PutMapping("/{contactId}/emails/{id}")
    public ResponseEntity<EmailDto> updateEmail(@PathVariable(value = "contactId") long contactId,
                                                    @PathVariable(value = "id") long emailId,
                                                    @Valid @RequestBody EmailDto emailDto) {
        EmailDto updatedEmail = emailService.updateEmail(contactId, emailId, emailDto);
        return new ResponseEntity<>(updatedEmail, HttpStatus.OK);
    }

    @DeleteMapping("/{contactId}/emails/{id}")
    public ResponseEntity<String> deleteEmail(@PathVariable(value = "contactId") long contactId, @PathVariable(value = "id") long emailId) {
        emailService.deleteEmail(contactId, emailId);
        return new ResponseEntity<>("Email deleted successfully", HttpStatus.OK);
    }
}
