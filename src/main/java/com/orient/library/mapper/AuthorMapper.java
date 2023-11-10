package com.orient.library.mapper;

import com.orient.library.dto.request.AuthorRequestDto;
import com.orient.library.dto.response.AuthorResponseDto;
import com.orient.library.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;

@Mapper(componentModel = "author")
public interface AuthorMapper {
    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    AuthorResponseDto entityToDto(Author author);
    Author dtoToEntity(AuthorRequestDto authorRequestDto);
}
