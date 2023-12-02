package com.orient.library.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PenaltyResponseDto {
    Long id;
    LeaseResponseDto lease;
    PenaltyTypeResponseDto penaltyType;
    Integer status;
    LocalDateTime createdAt;
}
