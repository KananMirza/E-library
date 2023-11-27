package com.orient.library.dto.request;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LeaseRequestDto {
    Long id;
    @NotNull(message = "Book is required!")
    Long bookId;
    @NotNull(message = "User is required!")
    Long userId;
    @NotNull(message = "Status is required!")
    Long status;
    @NotNull(message = "From date is required!")
    @FutureOrPresent(message = "From date must be in the future or present")
    LocalDateTime fromDate;
    @NotNull(message = "To date is required!")
    @FutureOrPresent(message = "To date must be in the future or present")
    LocalDateTime toDate;
}
