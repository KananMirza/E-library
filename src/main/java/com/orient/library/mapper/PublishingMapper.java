package com.orient.library.mapper;

import com.orient.library.dto.request.PublishingRequestDto;
import com.orient.library.dto.response.PublishingResponseDto;
import com.orient.library.entity.Publishing;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "publishing")
public interface PublishingMapper {
    PublishingMapper INSTANCE = Mappers.getMapper(PublishingMapper.class);
    PublishingResponseDto entityToDto(Publishing publishing);
    Publishing dtoToEntity(PublishingRequestDto publishingRequestDto);
}
