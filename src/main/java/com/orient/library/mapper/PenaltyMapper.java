package com.orient.library.mapper;

import com.orient.library.dto.request.PenaltyRequestDto;
import com.orient.library.dto.response.PenaltyResponseDto;
import com.orient.library.entity.Penalty;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PenaltyMapper {
    PenaltyMapper INSTANCE = Mappers.getMapper(PenaltyMapper.class);
    Penalty dtoToEntity(PenaltyRequestDto penaltyRequestDto);
    PenaltyResponseDto entityToDto(Penalty penalty);
}
