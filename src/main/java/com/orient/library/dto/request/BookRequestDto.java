package com.orient.library.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.Year;
import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookRequestDto {
    Long id;
    @NotBlank(message = "SeriaId is required!")
    String seriaId;
    @NotNull(message = "Shelf is required!")
    Integer shelfId;
    @NotBlank(message = "Title is required!")
    String title;
    @NotBlank(message = "Description is required!")
    String description;
    @NotNull(message = "Penalty amount is required!")
    Float penaltyAmount;
    @NotNull(message = "Year Publishing is required!")
    Year yearPublishing;
    @NotNull(message = "Count is required!")
    Integer count;
}
