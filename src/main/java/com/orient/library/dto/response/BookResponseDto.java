package com.orient.library.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.Date;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookResponseDto {
    Long id;
    String seriaId;
    Integer shelfId;
    String title;
    String description;
    Float penaltyAmount;
    Year yearPublishing;
    Integer count;
    Integer status;
    LocalDateTime createdAt;
    @JsonProperty("categories")
    List<CategoryResponseDto> categoryResponseDtos;
}
