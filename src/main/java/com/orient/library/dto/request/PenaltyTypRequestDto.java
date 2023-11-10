package com.orient.library.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PenaltyTypRequestDto {
    Long id;
    @NotBlank(message = "Name is required!")
    String name;
}
