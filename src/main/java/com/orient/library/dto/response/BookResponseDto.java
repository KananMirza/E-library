package com.orient.library.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookResponseDto {
    Long id;
    String seriaId;
    ShelfResponseDto shelf;
    @JsonProperty("categories")
    List<CategoryResponseDto> categories;
    List<AuthorResponseDto> authors;
    List<PublishingResponseDto> publishers;
    String title;
    String description;
    Float penaltyAmount;
    Year yearPublishing;
    Integer count;
    Integer status;
    String image;
    LocalDateTime createdAt;

}
