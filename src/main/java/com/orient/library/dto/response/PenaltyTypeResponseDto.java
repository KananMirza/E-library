package com.orient.library.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PenaltyTypeResponseDto {
    Long id;
    String name;
    LocalDateTime createdAt;
}
