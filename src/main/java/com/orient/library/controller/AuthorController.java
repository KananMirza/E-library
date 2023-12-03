package com.orient.library.controller;

import com.orient.library.dto.request.AuthorRequestDto;
import com.orient.library.dto.request.StatusRequestDto;
import com.orient.library.dto.response.AuthorResponseDto;
import com.orient.library.enums.Message;
import com.orient.library.exception.DataNotFoundException;
import com.orient.library.response.ResponseApi;
import com.orient.library.service.AuthorService;
import com.orient.library.util.Utility;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/author")
public class AuthorController {
    private final AuthorService authorService;
    private final Utility utility;

    @GetMapping("/get-all")
    public ResponseEntity<ResponseApi> getAllAuthor() {
        List<AuthorResponseDto> authors = authorService.getAllAuthor();
        return ResponseEntity.ok(utility.response(HttpStatus.OK.value(),
                Message.SUCCESS.value(), authors));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseApi> getAuthorById(@PathVariable @Valid @NotBlank(message = "Id is required!") Long id) {
        AuthorResponseDto author = authorService.getAuthorById(id);
        return ResponseEntity.ok(utility.response(HttpStatus.OK.value(),
                Message.SUCCESS.value(), author));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseApi> createAuthor(@RequestBody @Valid AuthorRequestDto authorRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(utility.response(HttpStatus.CREATED.value(),
                authorService.createAuthor(authorRequestDto),
                null));
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseApi> updateAuthor(@RequestBody @Valid AuthorRequestDto authorRequestDto) {
        return ResponseEntity.ok(utility.response(HttpStatus.OK.value(),
                authorService.updateAuthor(authorRequestDto),
                null));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseApi> deleteAuthor(@PathVariable @Valid @NotBlank(message = "Id is required!") Long id) {
        return ResponseEntity.ok(utility.response(HttpStatus.OK.value(),
                authorService.deleteAuthor(id),
                null));
    }

    @PostMapping("/change-status")
    public ResponseEntity<ResponseApi> changeStatus(@RequestBody @Valid StatusRequestDto StatusRequestDto) {
        return ResponseEntity.ok(utility.response(HttpStatus.OK.value(),
                authorService.changeStatus(StatusRequestDto),
                null));
    }
}
