package com.orient.library.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PenaltyRequestDto {
    Long id;
    @NotNull(message = "Lease is required!")
    Long leaseId;
    @NotNull(message = "Penalty Type is required!")
    Long penaltyTypeId;
}
