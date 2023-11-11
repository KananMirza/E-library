package com.orient.library.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShelfResponseDto {
    Long id;
    String shelfNo;
    Integer status;
    LocalDateTime createdAt;
}
