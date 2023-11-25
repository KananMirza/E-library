package com.orient.library.service;

import com.orient.library.dto.request.StatusRequestDto;
import com.orient.library.dto.response.UserResponseDto;

import java.util.List;

public interface UserService {
    List<UserResponseDto> getAllUserList();

    UserResponseDto findUserById(Long userId);

    String changeStatus(StatusRequestDto statusRequestDto);
}
