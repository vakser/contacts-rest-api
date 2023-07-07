package com.example.controller;

import com.example.dto.ContactDto;
import com.example.security.JwtTokenProvider;
import com.example.service.ContactService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {
    private ContactService contactService;
    private JwtTokenProvider jwtTokenProvider;

    public ContactController(ContactService contactService, JwtTokenProvider jwtTokenProvider) {
        this.contactService = contactService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping
    public ResponseEntity<ContactDto> createContact(@Valid @RequestBody ContactDto contactDto, HttpServletRequest request) {
        String username = getUserNameFromRequestHeader(request);
        contactDto.setUserEmail(username);
        return new ResponseEntity<>(contactService.createContact(contactDto), HttpStatus.CREATED);
    }

    private String getUserNameFromRequestHeader(HttpServletRequest request) {
        final String authHeader = request.getHeader("Authorization");
        String username = null;
        String jwt;
        if (authHeader != null && authHeader.startsWith("Bearer")) {
            jwt = authHeader.substring(7);
            username = jwtTokenProvider.getUsername(jwt);
        }
        return username;
    }

    @GetMapping
    public List<ContactDto> findAllContacts(HttpServletRequest request) {
        String username = getUserNameFromRequestHeader(request);
        return contactService.findAllContactsByUserEmail(username);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable(name = "id") long id) {
        contactService.deleteContactById(id);
        return new ResponseEntity<>("Contact deleted successfully!", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactDto> getContactById(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(contactService.getContactById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactDto> updateContact(@Valid @RequestBody ContactDto contactDto, @PathVariable(name = "id") long id) {
        ContactDto updatedContact = contactService.updateContact(contactDto, id);
        return new ResponseEntity<>(updatedContact, HttpStatus.OK);
    }

}
