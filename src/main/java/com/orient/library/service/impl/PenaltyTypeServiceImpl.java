package com.orient.library.service.impl;

import com.orient.library.dto.request.PenaltyTypeRequestDto;
import com.orient.library.dto.response.PenaltyTypeResponseDto;
import com.orient.library.entity.PenaltyType;
import com.orient.library.enums.DeleteType;
import com.orient.library.enums.Message;
import com.orient.library.exception.DataNotFoundException;
import com.orient.library.mapper.PenaltyTypeMapper;
import com.orient.library.repository.PenaltyTypeRepository;
import com.orient.library.service.PenaltyTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PenaltyTypeServiceImpl implements PenaltyTypeService {
    private final PenaltyTypeRepository penaltyTypeRepository;
    private final LocalDateTime CURRENT_TIME = LocalDateTime.now();

    @Override
    public List<PenaltyTypeResponseDto> getAllPenaltyTypes() {
        return penaltyTypeRepository.getPenaltyTypeByIsDeleted(DeleteType.NONDELETE.value()).stream()
                .map(PenaltyTypeMapper.INSTANCE::entityToDto).collect(Collectors.toList());
    }

    @Override
    public PenaltyTypeResponseDto getPenaltyTypeById(Long id) {
        return PenaltyTypeMapper.INSTANCE.entityToDto(findPenaltyType(id));
    }

    @Override
    public String createPenaltyType(PenaltyTypeRequestDto penaltyTypRequestDto) {
            PenaltyType penaltyType = PenaltyTypeMapper.INSTANCE.dtoToEntity(penaltyTypRequestDto);
            penaltyTypeRepository.save(penaltyType);
            return "PenaltyType has been successfully created";
    }

    @Override
    public String updatePenaltyType(PenaltyTypeRequestDto penaltyTypRequestDto) {
        PenaltyType penaltyType = findPenaltyType(penaltyTypRequestDto.getId());
        penaltyType.setName(penaltyTypRequestDto.getName());
        penaltyType.setUpdatedAt(CURRENT_TIME);
        penaltyTypeRepository.save(penaltyType);
        return "PenaltyType has been successfully updated";
    }

    @Override
    public String deletePenaltyType(Long id) {
        PenaltyType penaltyType = findPenaltyType(id);
        penaltyType.setIsDeleted(DeleteType.DELETE.value());
        penaltyType.setUpdatedAt(CURRENT_TIME);
        penaltyTypeRepository.save(penaltyType);
        return "PenaltyType has been successfully deleted!";
    }

    public PenaltyType findPenaltyType(Long id) {
        PenaltyType penaltyType = penaltyTypeRepository.findPenaltyTypeByIdAndIsDeleted(id, DeleteType.NONDELETE.value());
        if (penaltyType == null) {
            throw new DataNotFoundException(Message.PENALTY_TYPE_NOT_FOUND.value());
        }
        return penaltyType;
    }
}
