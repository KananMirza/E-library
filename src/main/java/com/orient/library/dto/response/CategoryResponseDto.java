package com.orient.library.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryResponseDto {
    Long id;
    String name;
    Integer status;
    LocalDateTime createdAt;

}
