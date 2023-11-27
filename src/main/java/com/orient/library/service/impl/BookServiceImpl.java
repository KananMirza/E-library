package com.orient.library.service.impl;

import com.orient.library.dto.request.BookRequestDto;
import com.orient.library.dto.request.StatusRequestDto;
import com.orient.library.dto.response.BookResponseDto;
import com.orient.library.entity.*;
import com.orient.library.enums.DeleteType;
import com.orient.library.enums.Message;
import com.orient.library.enums.Status;
import com.orient.library.exception.DataNotFoundException;
import com.orient.library.mapper.BookMapper;
import com.orient.library.repository.AuthorRepository;
import com.orient.library.repository.BookRepository;
import com.orient.library.repository.CategoryRepository;
import com.orient.library.repository.PublishingRepository;
import com.orient.library.service.BookService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final ShelfServiceImpl shelfService;
    private final CategoryRepository categoryRepository;
    private final AuthorRepository authorRepository;
    private final PublishingRepository publishingRepository;

    @Override
    public List<BookResponseDto> getAllBooks() {
        return bookRepository.getBooksByIsDeleted(DeleteType.NONDELETE.value())
                .stream()
                .map(BookMapper.INSTANCE::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookResponseDto getBookById(Long id) {
        return BookMapper.INSTANCE.entityToDto(findBook(id));
    }

    @Override
    public String createBook(BookRequestDto bookRequestDto) {
        Book book = BookMapper.INSTANCE.dtoToEntity(bookRequestDto);
        Shelf shelf = shelfService.findShelf(bookRequestDto.getShelfId());
        List<Category> categories = getCategoryList(bookRequestDto.getCategoriesId());
        List<Author> authors = getAuthorList(bookRequestDto.getAuthorsId());
        List<Publishing> publishers = getPublishingList(bookRequestDto.getPublishersId());
        book.setShelf(shelf);
        book.setCategories(categories);
        book.setAuthors(authors);
        book.setPublishers(publishers);
        bookRepository.save(book);
        return "Book has been successfully created";
    }

    @Override
    public String updateBook(BookRequestDto bookRequestDto) {
        Book book = findBook(bookRequestDto.getId());
        Shelf shelf = shelfService.findShelf(bookRequestDto.getShelfId());
        List<Category> categories = getCategoryList(bookRequestDto.getCategoriesId());
        List<Author> authors = getAuthorList(bookRequestDto.getAuthorsId());
        List<Publishing> publishers = getPublishingList(bookRequestDto.getPublishersId());
        setBookFields(book, shelf, bookRequestDto, categories, authors, publishers);
        book.setUpdatedAt(LocalDateTime.now());
        bookRepository.save(book);
        return "Book has been successfully updated!";
    }

    @Override
    public String deleteBook(Long id) {
        Book book = findBook(id);
        book.setIsDeleted(DeleteType.DELETE.value());
        book.setUpdatedAt(LocalDateTime.now());
        bookRepository.save(book);
        return "Book has been successfully deleted!";
    }

    @Override
    public String changeStatus(StatusRequestDto statusRequestDto) {
        Book book = findBook(statusRequestDto.getId());
        book.setStatus(statusRequestDto.getStatus());
        book.setUpdatedAt(LocalDateTime.now());
        bookRepository.save(book);
        return "Book status has been successfully changed!";
    }

    public Book findBook(Long id) {
        Book book = bookRepository.findBookByIdAndIsDeleted(id, DeleteType.NONDELETE.value());
        if (book == null) {
            throw new DataNotFoundException(Message.BOOK_NOT_FOUND.value());
        }
        return book;
    }

    private List<Category> getCategoryList(List<Long> categoryIds) {
        return categoryRepository.findAllByIdInAndIsDeletedAndStatus(categoryIds,
                DeleteType.NONDELETE.value(), Status.ACTIVE.value());
    }

    private List<Author> getAuthorList(List<Long> authorIds) {
        return authorRepository.findAllByIdInAndIsDeletedAndStatus(authorIds,
                DeleteType.NONDELETE.value(), Status.ACTIVE.value());
    }

    private List<Publishing> getPublishingList(List<Long> publishingIds) {
        return publishingRepository.findAllByIdInAndIsDeletedAndStatus(publishingIds,
                DeleteType.NONDELETE.value(), Status.ACTIVE.value());
    }

    private void setBookFields(Book book, Shelf shelf, BookRequestDto bookRequestDto,
                               List<Category> categories, List<Author> authors, List<Publishing> publishers) {
        book.setShelf(shelf);
        book.setSeriaId(bookRequestDto.getSeriaId());
        book.setCategories(categories);
        book.setAuthors(authors);
        book.setPublishers(publishers);
        book.setTitle(bookRequestDto.getTitle());
        book.setDescription(bookRequestDto.getDescription());
        book.setPenaltyAmount(bookRequestDto.getPenaltyAmount());
        book.setYearPublishing(bookRequestDto.getYearPublishing());
        book.setCount(bookRequestDto.getCount());
        book.setUpdatedAt(LocalDateTime.now());
    }
}
