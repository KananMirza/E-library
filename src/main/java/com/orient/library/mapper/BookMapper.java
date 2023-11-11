package com.orient.library.mapper;

import com.orient.library.dto.request.BookRequestDto;
import com.orient.library.dto.response.BookResponseDto;
import com.orient.library.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "book")
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);
    @Mapping(source = "shelf", target = "shelf")
    BookResponseDto entityToDto(Book book);
    Book dtoToEntity(BookRequestDto bookRequestDto);
}
