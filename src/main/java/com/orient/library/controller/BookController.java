package com.orient.library.controller;

import com.orient.library.dto.request.BookRequestDto;
import com.orient.library.dto.request.StatusRequestDto;
import com.orient.library.dto.response.BookResponseDto;
import com.orient.library.enums.Message;
import com.orient.library.response.ResponseApi;
import com.orient.library.service.BookService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/book")
public class BookController {
    private final BookService bookService;
    private final ResponseApi responseApi;

    @GetMapping("/get-all")
    public ResponseEntity<ResponseApi> getAllBooks(){
        List<BookResponseDto> books = bookService.getAllBooks();
        return ResponseEntity.ok(responseApi(HttpStatus.OK.value(), Message.SUCCESS.value(), books));
    }

    @GetMapping("/get/{id}")
    public  ResponseEntity<ResponseApi> getBookById(@PathVariable @Valid @NotNull(message = "Id is required") Long id){
        BookResponseDto book = bookService.getBookById(id);
        return ResponseEntity.ok(responseApi(HttpStatus.OK.value(), Message.SUCCESS.value(), book));
    }
    @PostMapping("/create")
    public ResponseEntity<ResponseApi> createBook(@RequestBody @Valid BookRequestDto bookRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(responseApi(HttpStatus.OK.value()
        ,bookService.createBook(bookRequestDto),null));
    }
    @PostMapping("/update")
    public ResponseEntity<ResponseApi> updateBook(@RequestBody @Valid BookRequestDto bookRequestDto){
        return ResponseEntity.ok(responseApi(HttpStatus.OK.value(),bookService.updateBook(bookRequestDto),null));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseApi> deleteBook(@PathVariable @Valid @NotNull(message = "Id is required") Long id){
        return ResponseEntity.ok(responseApi(HttpStatus.OK.value(),bookService.deleteBook(id),null));
    }
    @PostMapping("/change-status")
    public ResponseEntity<ResponseApi> changeStatus(@RequestBody @Valid StatusRequestDto statusRequestDto){
        return ResponseEntity.ok(responseApi(HttpStatus.OK.value(), bookService.changeStatus(statusRequestDto),null));
    }


    private ResponseApi responseApi(Integer status, String message, Object object) {
        responseApi.setMessage(message);
        responseApi.setStatus(status);
        responseApi.setBody(object);
        return responseApi;
    }
}
