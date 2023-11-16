package com.orient.library.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.Year;
import java.util.Date;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookRequestDto {
    Long id;
    @NotBlank(message = "SeriaId is required!")
    String seriaId;
    @NotNull(message = "Shelf is required!")
    Long shelfId;
    @NotBlank(message = "Title is required!")
    String title;
    @NotNull(message = "At least one category must be selected")
    List<Long> categoriesId;
    @NotNull(message = "At least one author must be selected")
    List<Long> authorsId;
    @NotNull(message = "At least one publisher must be selected")
    List<Long> publishersId;
    @NotBlank(message = "Description is required!")
    String description;
    @NotNull(message = "Penalty amount is required!")
    Float penaltyAmount;
    @NotNull(message = "Year Publishing is required!")
    Year yearPublishing;
    @NotNull(message = "Count is required!")
    Integer count;
}
