package com.orient.library.service.impl;

import com.orient.library.dto.request.CategoryRequestDto;
import com.orient.library.dto.request.StatusRequestDto;
import com.orient.library.dto.response.CategoryResponseDto;
import com.orient.library.entity.Category;
import com.orient.library.enums.DeleteType;
import com.orient.library.enums.Message;
import com.orient.library.exception.DataNotFoundException;
import com.orient.library.mapper.CategoryMapper;
import com.orient.library.repository.CategoryRepository;
import com.orient.library.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private static final LocalDateTime CURRENT_DATE = LocalDateTime.now();
    private static final String NOT_FOUND = "Category not found!";
    @Override
    public List<CategoryResponseDto> getAllCategory() {
        return categoryRepository.getCategoriesByIsDeleted(DeleteType.NONDELETE.value())
                .stream().map(CategoryMapper.INSTANCE::entityToDto).collect(Collectors.toList());
    }

    @Override
    public CategoryResponseDto getCategoryById(Long id) {
        return CategoryMapper.INSTANCE.entityToDto(findCategory(id));
    }

    @Override
    public String createCategory(CategoryRequestDto categoryRequestDto) {
        Category category = CategoryMapper.INSTANCE.dtoToEntity(categoryRequestDto);
        categoryRepository.save(category);
        return "Category has been successfully created";
    }

    @Override
    public String updateCategory(CategoryRequestDto categoryRequestDto) {
        Category category = findCategory(categoryRequestDto.getId());
        category.setName(categoryRequestDto.getName());
        category.setUpdatedAt(CURRENT_DATE);
        categoryRepository.save(category);
        return "Category has been successfully updated!";
    }

    @Override
    public String deleteCategory(Long id) {
        Category category = findCategory(id);
        category.setIsDeleted(DeleteType.DELETE.value());
        category.setUpdatedAt(CURRENT_DATE);
        categoryRepository.save(category);
        return "Category has been successfully deleted!";
    }

    @Override
    public String changeStatus(StatusRequestDto requestStatus) {
        Category category = findCategory(requestStatus.getId());
        category.setStatus(requestStatus.getStatus());
        category.setUpdatedAt(CURRENT_DATE);
        categoryRepository.save(category);
        return "Category status has been successfully changed!";
    }
    private Category findCategory(Long id){
        Category category = categoryRepository.findCategoryByIdAndIsDeleted(id,
                DeleteType.NONDELETE.value());
        if(category == null){
            throw new DataNotFoundException(Message.CATEGORY_NOT_FOUND.value());
        }
        return category;
    }
}
