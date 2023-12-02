package com.orient.library.service.impl;

import com.orient.library.dto.request.LeaseRequestDto;
import com.orient.library.dto.response.LeaseResponseDto;
import com.orient.library.entity.Book;
import com.orient.library.entity.Lease;
import com.orient.library.entity.LeaseStatus;
import com.orient.library.entity.User;
import com.orient.library.enums.Message;
import com.orient.library.exception.DataNotFoundException;
import com.orient.library.mapper.LeaseMapper;
import com.orient.library.repository.LeaseRepository;
import com.orient.library.service.LeaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class LeaseServiceImpl implements LeaseService {
    private final LeaseRepository leaseRepository;
    private final BookServiceImpl bookService;
    private final UserServiceImpl userService;
    private final LeaseStatusServiceImpl leaseStatusService;
    @Override
    public List<LeaseResponseDto> getAllLease() {
        return leaseRepository.findAll().stream().map(LeaseMapper.INSTANCE::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public LeaseResponseDto getLeaseById(Long leaseId) {
        return LeaseMapper.INSTANCE.entityToDto(findById(leaseId));
    }

    @Override
    public String createLease(LeaseRequestDto leaseRequestDto) {
        Lease lease = LeaseMapper.INSTANCE.dtoToEntity(leaseRequestDto);
        createOrUpdate(leaseRequestDto, lease);
        return "Lease has been successfully created!";
    }

    @Override
    public String updateLease(LeaseRequestDto leaseRequestDto) {
        Lease lease = findById(leaseRequestDto.getId());
        createOrUpdate(leaseRequestDto, lease);
        return "Lease has been successfully updated!";
    }

    private void createOrUpdate(LeaseRequestDto leaseRequestDto, Lease lease) {
        Book book = bookService.findBook(leaseRequestDto.getBookId());
        User user = userService.findUser(leaseRequestDto.getUserId());
        LeaseStatus status = leaseStatusService.findLeaseStatus(leaseRequestDto.getStatusId());
        List<LeaseStatus> statuses = new ArrayList<>();
        statuses.add(status);
        lease.setStatuses(statuses);
        lease.setBook(book);
        lease.setUser(user);
        leaseRepository.save(lease);
    }

    private Lease findById(Long leaseId){
        Lease lease = leaseRepository.findLeaseById(leaseId);
        if(lease == null){
            throw new DataNotFoundException(Message.LEASE_NOT_FOUND.value());
        }
        return lease;
    }
}
