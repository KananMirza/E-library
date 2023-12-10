package com.orient.library.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PublishingResponseDto {
    Long id;
    String name;
    String description;
    String address;
    Integer status;
    LocalDateTime createdAt;
}
