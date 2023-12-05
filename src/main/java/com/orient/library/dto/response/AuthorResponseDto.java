package com.orient.library.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthorResponseDto {
    Long id;
    String name;
    String surname;
    String description;
    String image;
    Integer status;
    LocalDateTime createdAt;
}
