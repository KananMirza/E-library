package com.orient.library.service;

import com.orient.library.dto.request.UserRequestDto;

public interface AuthService {
    String register(UserRequestDto registerRequestDto);
}
