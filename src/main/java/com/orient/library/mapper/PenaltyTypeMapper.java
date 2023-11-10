package com.orient.library.mapper;

import com.orient.library.dto.request.PenaltyTypRequestDto;
import com.orient.library.dto.response.PenaltyTypeResponseDto;
import com.orient.library.entity.PenaltyType;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "penalty_types")
public interface PenaltyTypeMapper {
    PenaltyTypeMapper INSTANCE = Mappers.getMapper(PenaltyTypeMapper.class);

    PenaltyTypeResponseDto entityToDto(PenaltyType penaltyType);

    PenaltyType dtoToEntity(PenaltyTypRequestDto penaltyTypRequestDto);
}
