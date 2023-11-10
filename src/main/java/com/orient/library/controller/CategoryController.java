package com.orient.library.controller;

import com.orient.library.dto.request.CategoryRequestDto;
import com.orient.library.dto.request.StatusRequestDto;
import com.orient.library.dto.response.CategoryResponseDto;
import com.orient.library.enums.Message;
import com.orient.library.exception.DataNotFoundException;
import com.orient.library.response.ResponseApi;
import com.orient.library.service.CategoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/category")
public class CategoryController {
    private final CategoryService categoryService;
    private final ResponseApi responseApi;

    @GetMapping("/get-all")
    public ResponseEntity<ResponseApi> getAllCategory() {
        List<CategoryResponseDto> categories = categoryService.getAllCategory();
        return ResponseEntity.ok(responseApi(HttpStatus.OK.value(), Message.SUCCESS.value(),categories));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseApi> getCategoryById(@PathVariable @Valid @NotNull(message = "Id is required") Long id) {
        CategoryResponseDto category = categoryService.getCategoryById(id);
        if (category == null) {
            throw new DataNotFoundException(Message.CATEGORY_NOT_FOUND.value());
        }
        return ResponseEntity.ok(responseApi(HttpStatus.OK.value(), Message.SUCCESS.value(),category));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseApi> createCategory(@RequestBody @Valid CategoryRequestDto categoryRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(responseApi(HttpStatus.CREATED.value(),
                categoryService.createCategory(categoryRequestDto),null));
    }
    @PostMapping("/update")
    public ResponseEntity<ResponseApi> updateCategory(@RequestBody @Valid CategoryRequestDto categoryRequestDto){
        return ResponseEntity.ok(responseApi(HttpStatus.OK.value(),categoryService.updateCategory(categoryRequestDto),null));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseApi> deleteCategory(@PathVariable @Valid @NotNull(message = "Id is required") Long id){
        return ResponseEntity.ok(responseApi(HttpStatus.OK.value(), categoryService.deleteCategory(id),null));
    }
    @PostMapping("/change-status")
    public ResponseEntity<ResponseApi> changeStatus(@RequestBody @Valid StatusRequestDto statusRequestDto){
        return ResponseEntity.ok(responseApi(HttpStatus.OK.value(),categoryService.changeStatus(statusRequestDto),null));
    }

    private ResponseApi responseApi(Integer status, String message, Object object) {
        responseApi.setMessage(message);
        responseApi.setStatus(status);
        responseApi.setBody(object);
        return responseApi;
    }
}
