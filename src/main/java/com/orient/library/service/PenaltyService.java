package com.orient.library.service;

import com.orient.library.dto.request.PenaltyRequestDto;
import com.orient.library.dto.response.PenaltyResponseDto;

import java.util.List;

public interface PenaltyService {
    List<PenaltyResponseDto> getAllPenalty();
    PenaltyResponseDto getPenaltyById(Long id);
    String createPenalty(PenaltyRequestDto penaltyRequestDto);
    String updatePenalty(PenaltyRequestDto penaltyRequestDto);
}
