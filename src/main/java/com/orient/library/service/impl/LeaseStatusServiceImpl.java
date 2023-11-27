package com.orient.library.service.impl;

import com.orient.library.dto.request.LeaseStatusRequestDto;
import com.orient.library.dto.response.LeaseStatusResponseDto;
import com.orient.library.entity.LeaseStatus;
import com.orient.library.enums.DeleteType;
import com.orient.library.enums.Message;
import com.orient.library.exception.DataNotFoundException;
import com.orient.library.mapper.LeaseStatusMapper;
import com.orient.library.repository.LeaseStatusRepository;
import com.orient.library.service.LeaseStatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class LeaseStatusServiceImpl implements LeaseStatusService {
    private final LeaseStatusRepository leaseStatusRepository;
    private final LocalDateTime CURRENT_TIME = LocalDateTime.now();
    @Override
    public List<LeaseStatusResponseDto> getAllLeaseStatus() {
        return leaseStatusRepository.getLeaseStatusesByIsDeleted(DeleteType.NONDELETE.value()).stream()
                .map(LeaseStatusMapper.INSTANCE::entityToDto).collect(Collectors.toList());
    }

    @Override
    public LeaseStatusResponseDto getLeaseStatusById(Long id) {
        return LeaseStatusMapper.INSTANCE.entityToDto(findLeaseStatus(id));
    }

    @Override
    public String createLeaseStatus(LeaseStatusRequestDto leaseStatusRequestDto) {
        LeaseStatus leaseStatus = LeaseStatusMapper.INSTANCE.dtoToEntity(leaseStatusRequestDto);
        leaseStatusRepository.save(leaseStatus);
        return "LeaseStatus has been successfully created";
    }

    @Override
    public String updateLeaseStatus(LeaseStatusRequestDto leaseStatusRequestDto) {
        LeaseStatus leaseStatus = findLeaseStatus(leaseStatusRequestDto.getId());
        leaseStatus.setName(leaseStatusRequestDto.getName());
        leaseStatus.setUpdatedAt(CURRENT_TIME);
        leaseStatusRepository.save(leaseStatus);
        return "LeaseStatus has been successfully updated";
    }

    @Override
    public String deleteLeaseStatus(Long id) {
        LeaseStatus leaseStatus = findLeaseStatus(id);
        leaseStatus.setIsDeleted(DeleteType.DELETE.value());
        leaseStatus.setUpdatedAt(CURRENT_TIME);
        leaseStatusRepository.save(leaseStatus);
        return "LeaseStatus has been successfully deleted!";
    }

    public LeaseStatus findLeaseStatus(Long id){
        LeaseStatus leaseStatus = leaseStatusRepository.findByIdAndIsDeleted(id
                ,DeleteType.NONDELETE.value());
        if(leaseStatus == null){
            throw new DataNotFoundException(Message.LEASE_STATUS_NOT_FOUND.value());
        }
        return leaseStatus;
    }
}
