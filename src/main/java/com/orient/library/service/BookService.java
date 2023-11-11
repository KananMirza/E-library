package com.orient.library.service;

import com.orient.library.dto.request.BookRequestDto;
import com.orient.library.dto.request.StatusRequestDto;
import com.orient.library.dto.response.BookResponseDto;

import java.util.List;

public interface BookService {
    List<BookResponseDto> getAllBooks();
    BookResponseDto getBookById(Long id);
    String createBook(BookRequestDto bookRequestDto);
    String updateBook(BookRequestDto bookRequestDto);
    String deleteBook(Long id);
    String changeStatus(StatusRequestDto statusRequestDto);
}
