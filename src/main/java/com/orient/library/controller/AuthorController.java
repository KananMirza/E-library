package com.orient.library.controller;

import com.orient.library.dto.request.AuthorRequestDto;
import com.orient.library.dto.request.StatusRequestDto;
import com.orient.library.dto.response.AuthorResponseDto;
import com.orient.library.enums.Message;
import com.orient.library.exception.DataNotFoundException;
import com.orient.library.response.ResponseApi;
import com.orient.library.service.AuthorService;
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
    private final ResponseApi responseApi;

    @GetMapping("/get-all")
    public ResponseEntity<ResponseApi> getAllAuthor(){
        List<AuthorResponseDto> authors = authorService.getAllAuthor();
        return ResponseEntity.ok(responseApi(HttpStatus.OK.value(),
                Message.SUCCESS.value(),authors));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseApi> getAuthorById(@PathVariable @Valid @NotBlank(message = "Id is required!") Long id){
        AuthorResponseDto author = authorService.getAuthorById(id);
        if(author == null){
            throw new DataNotFoundException("No authors found!");
        }
        return ResponseEntity.ok(responseApi(HttpStatus.OK.value(),
                Message.SUCCESS.value(),author));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseApi> createAuthor(@RequestBody @Valid AuthorRequestDto authorRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(this.responseApi(HttpStatus.CREATED.value(),
                Message.SUCCESS.value(),
                authorService.createAuthor(authorRequestDto)));
    }
    @PostMapping("/update")
    public ResponseEntity<ResponseApi> updateAuthor(@RequestBody @Valid AuthorRequestDto authorRequestDto){
        return ResponseEntity.ok(this.responseApi(HttpStatus.OK.value(),
                authorService.updateAuthor(authorRequestDto),
                null));
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseApi> deleteAuthor(@PathVariable @Valid @NotBlank(message = "Id is required!") Long id){
        return ResponseEntity.ok(this.responseApi(HttpStatus.OK.value(),
                authorService.deleteAuthor(id),
                null));
    }

    @PostMapping("/change-status")
    public ResponseEntity<ResponseApi> changeStatus(@RequestBody @Valid StatusRequestDto StatusRequestDto){
        return ResponseEntity.ok(this.responseApi(HttpStatus.OK.value(),
                authorService.changeStatus(StatusRequestDto),
                null));
    }

    private ResponseApi responseApi(Integer status,String message,Object object){
        responseApi.setMessage(message);
        responseApi.setStatus(status);
        responseApi.setBody(object);
        return responseApi;
    }
}
