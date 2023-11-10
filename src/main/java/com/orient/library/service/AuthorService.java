package com.orient.library.service;

import com.orient.library.dto.request.AuthorRequestDto;
import com.orient.library.dto.request.StatusRequestDto;
import com.orient.library.dto.response.AuthorResponseDto;

import java.util.List;

public interface AuthorService {
    List<AuthorResponseDto> getAllAuthor();
    AuthorResponseDto getAuthorById(Long id);
    String createAuthor(AuthorRequestDto requestAuthor);
    String updateAuthor(AuthorRequestDto requestAuthor);
    String deleteAuthor(Long id);
    String changeStatus(StatusRequestDto requestStatus);
}
