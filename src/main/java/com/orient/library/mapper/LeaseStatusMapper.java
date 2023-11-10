package com.orient.library.mapper;

import com.orient.library.dto.request.LeaseStatusRequestDto;
import com.orient.library.dto.response.LeaseStatusResponseDto;
import com.orient.library.entity.LeaseStatus;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "lease_status")
public interface LeaseStatusMapper {
    LeaseStatusMapper INSTANCE = Mappers.getMapper(LeaseStatusMapper.class);
    LeaseStatusResponseDto entityToDto(LeaseStatus leaseStatus);
    LeaseStatus dtoToEntity(LeaseStatusRequestDto leaseStatusRequestDto);
}
