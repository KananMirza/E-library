package com.orient.library.service.impl;

import com.orient.library.dto.request.BookRequestDto;
import com.orient.library.dto.request.StatusRequestDto;
import com.orient.library.dto.response.BookResponseDto;
import com.orient.library.entity.Book;
import com.orient.library.enums.DeleteType;
import com.orient.library.enums.Message;
import com.orient.library.exception.DataNotFoundException;
import com.orient.library.mapper.BookMapper;
import com.orient.library.repository.BookRepository;
import com.orient.library.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final LocalDateTime CURRENT_TIME = LocalDateTime.now();
    @Override
    public List<BookResponseDto> getAllBooks() {
        return bookRepository.getBooksByIsDeleted(DeleteType.NONDELETE.value()).stream().map(
                BookMapper.INSTANCE::entityToDto).collect(Collectors.toList());
    }

    @Override
    public BookResponseDto getBookById(Long id) {
        return BookMapper.INSTANCE.entityToDto(findBook(id));
    }

    @Override
    public String createBook(BookRequestDto bookRequestDto) {
        Book book = BookMapper.INSTANCE.dtoToEntity(bookRequestDto);
        bookRepository.save(book);
        return "Book has been successfully created";
    }

    @Override
    public String updateBook(BookRequestDto bookRequestDto) {
        Book book = findBook(bookRequestDto.getId());
        book.setSeriaId(bookRequestDto.getSeriaId());
        book.setShelfId(bookRequestDto.getShelfId());
        book.setTitle(bookRequestDto.getTitle());
        book.setDescription(bookRequestDto.getDescription());
        book.setPenaltyAmount(bookRequestDto.getPenaltyAmount());
        book.setYearPublishing(bookRequestDto.getYearPublishing());
        book.setCount(bookRequestDto.getCount());
        book.setUpdatedAt(CURRENT_TIME);
        bookRepository.save(book);
        return "Book has been successfully updated!";
    }

    @Override
    public String deleteBook(Long id) {
        Book book = findBook(id);
        book.setIsDeleted(DeleteType.DELETE.value());
        book.setUpdatedAt(CURRENT_TIME);
        bookRepository.save(book);
        return "Book has been successfully deleted!";
    }

    @Override
    public String changeStatus(StatusRequestDto statusRequestDto) {
        Book book = findBook(statusRequestDto.getId());
        book.setStatus(statusRequestDto.getStatus());
        book.setUpdatedAt(CURRENT_TIME);
        bookRepository.save(book);
        return "Book status has been successfully changed!";
    }
    private Book findBook(Long id){
        Book book = bookRepository.findBookByIdAndIsDeleted(id,DeleteType.NONDELETE.value());
        if(book == null){
            throw new DataNotFoundException(Message.BOOK_NOT_FOUND.value());
        }
        return book;
    }
}
