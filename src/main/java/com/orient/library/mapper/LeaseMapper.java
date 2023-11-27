package com.orient.library.mapper;

import com.orient.library.dto.request.LeaseRequestDto;
import com.orient.library.dto.response.LeaseResponseDto;
import com.orient.library.entity.Lease;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "lease")
public interface LeaseMapper {
    LeaseMapper INSTANCE = Mappers.getMapper(LeaseMapper.class);

    LeaseResponseDto entityToDto(Lease lease);

    Lease dtoToEntity(LeaseRequestDto leaseRequestDto);
}
