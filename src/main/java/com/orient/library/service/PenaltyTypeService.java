package com.orient.library.service;

import com.orient.library.dto.request.PenaltyTypeRequestDto;
import com.orient.library.dto.response.PenaltyTypeResponseDto;

import java.util.List;

public interface PenaltyTypeService {
    List<PenaltyTypeResponseDto> getAllPenaltyTypes();
    PenaltyTypeResponseDto getPenaltyTypeById(Long id);
    String createPenaltyType(PenaltyTypeRequestDto penaltyTypRequestDto);
    String updatePenaltyType(PenaltyTypeRequestDto penaltyTypRequestDto);
    String deletePenaltyType(Long id);
}
