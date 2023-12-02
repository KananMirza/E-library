package com.orient.library.service.impl;

import com.orient.library.dto.request.PenaltyRequestDto;
import com.orient.library.dto.response.PenaltyResponseDto;
import com.orient.library.entity.Lease;
import com.orient.library.entity.Penalty;
import com.orient.library.entity.PenaltyType;
import com.orient.library.enums.Message;
import com.orient.library.exception.DataNotFoundException;
import com.orient.library.mapper.PenaltyMapper;
import com.orient.library.repository.PenaltyRepository;
import com.orient.library.service.PenaltyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PenaltyServiceImpl implements PenaltyService {
    private final PenaltyRepository penaltyRepository;
    private final LeaseServiceImpl leaseService;
    private final PenaltyTypeServiceImpl penaltyTypeService;
    @Override
    public List<PenaltyResponseDto> getAllPenalty() {
        return penaltyRepository.findAll().stream()
                .map(PenaltyMapper.INSTANCE::entityToDto).collect(Collectors.toList());
    }

    @Override
    public PenaltyResponseDto getPenaltyById(Long id) {
        return PenaltyMapper.INSTANCE.entityToDto(findById(id));
    }

    @Override
    public String createPenalty(PenaltyRequestDto penaltyRequestDto) {
        Penalty penalty = PenaltyMapper.INSTANCE.dtoToEntity(penaltyRequestDto);
        Lease lease = leaseService.findById(penaltyRequestDto.getLeaseId());
        PenaltyType penaltyType = penaltyTypeService.findPenaltyType(penaltyRequestDto.getPenaltyTypeId());
        penalty.setPenaltyType(penaltyType);
        penalty.setLease(lease);
        penaltyRepository.save(penalty);
        return "Penalty has been successfully created";
    }

    @Override
    public String updatePenalty(PenaltyRequestDto penaltyRequestDto) {
        Penalty penalty  = findById(penaltyRequestDto.getId());
        Lease lease = leaseService.findById(penaltyRequestDto.getLeaseId());
        PenaltyType penaltyType = penaltyTypeService.findPenaltyType(penaltyRequestDto.getPenaltyTypeId());
        penalty.setPenaltyType(penaltyType);
        penalty.setLease(lease);
        penalty.setUpdatedAt(LocalDateTime.now());
        penaltyRepository.save(penalty);
        return "Penalty has been successfully updated!";
    }

    private Penalty findById(Long id){
        Optional<Penalty> optionalPenalty = penaltyRepository.findById(id);
        if(optionalPenalty.isEmpty()){
            throw new DataNotFoundException(Message.PENALTY_NOT_FOUND.value());
        }
        return optionalPenalty.get();
    }
}
