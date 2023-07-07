package com.example.repository;

import com.example.entity.Email;
import com.example.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhoneRepository extends JpaRepository<Phone, Long> {
    List<Phone> findByContactId(long contactId);
}
