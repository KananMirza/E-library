package com.orient.library.mapper;

import com.orient.library.dto.request.CategoryRequestDto;
import com.orient.library.dto.response.CategoryResponseDto;
import com.orient.library.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "category")
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
    CategoryResponseDto entityToDto(Category category);
    Category dtoToEntity(CategoryRequestDto categoryRequestDto);
}
