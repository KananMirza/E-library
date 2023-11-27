package com.orient.library.service;

import com.orient.library.dto.request.LeaseRequestDto;
import com.orient.library.dto.response.LeaseResponseDto;

import java.util.List;

public interface LeaseService {
    List<LeaseResponseDto> getAllLease();
    LeaseResponseDto getLeaseById(Long leaseId);
    String createLease(LeaseRequestDto leaseRequestDto);
    String updateLease(LeaseRequestDto leaseRequestDto);

    String updateLeaseStatus();
}
