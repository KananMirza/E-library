package com.orient.library.service;

import com.orient.library.dto.request.PenaltyTypRequestDto;
import com.orient.library.dto.response.PenaltyTypeResponseDto;

import java.util.List;

public interface PenaltyTypeService {
    List<PenaltyTypeResponseDto> getAllPenaltyTypes();
    PenaltyTypeResponseDto getPenaltyTypeById(Long id);
    String createPenaltyType(PenaltyTypRequestDto penaltyTypRequestDto);
    String updatePenaltyType(PenaltyTypRequestDto penaltyTypRequestDto);
    String deletePenaltyType(Long id);
}
