package com.orient.library.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LeaseResponseDto {
    Long id;
    BookResponseDto book;
    UserResponseDto user;
    List<LeaseStatusResponseDto> statuses;
    LocalDateTime fromDate;
    LocalDateTime toDate;
    LocalDateTime createdAt;
}
