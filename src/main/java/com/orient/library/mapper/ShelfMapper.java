package com.orient.library.mapper;

import com.orient.library.dto.request.ShelfRequestDto;
import com.orient.library.dto.response.ShelfResponseDto;
import com.orient.library.entity.Shelf;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "shelf")
public interface ShelfMapper {
    ShelfMapper INSTANCE = Mappers.getMapper(ShelfMapper.class);
    ShelfResponseDto entityToDto(Shelf shelf);
    Shelf dtoToEntity(ShelfRequestDto shelfRequestDto);
}
