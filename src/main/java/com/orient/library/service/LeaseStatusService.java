package com.orient.library.service;

import com.orient.library.dto.request.LeaseStatusRequestDto;
import com.orient.library.dto.response.LeaseStatusResponseDto;
import com.orient.library.entity.LeaseStatus;

import java.util.List;

public interface LeaseStatusService {
    List<LeaseStatusResponseDto> getAllLeaseStatus();
    LeaseStatusResponseDto getLeaseStatusById(Long id);
    String createLeaseStatus(LeaseStatusRequestDto leaseStatusRequestDto);
    String updateLeaseStatus(LeaseStatusRequestDto leaseStatusRequestDto);
    String deleteLeaseStatus(Long id);
}
