package com.example.service;

import com.example.dto.LoginDto;
import com.example.dto.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);
}
