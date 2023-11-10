package com.orient.library.service;

import com.orient.library.dto.request.CategoryRequestDto;
import com.orient.library.dto.request.StatusRequestDto;
import com.orient.library.dto.response.CategoryResponseDto;

import java.util.List;

public interface CategoryService {

    List<CategoryResponseDto> getAllCategory();
    CategoryResponseDto getCategoryById(Long id);
    String createCategory(CategoryRequestDto categoryRequestDto);
    String updateCategory(CategoryRequestDto categoryRequestDto);
    String deleteCategory(Long id);
    String changeStatus(StatusRequestDto requestStatus);
}
