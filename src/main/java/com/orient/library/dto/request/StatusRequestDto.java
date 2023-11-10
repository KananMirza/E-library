package com.orient.library.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StatusRequestDto {
    @NotNull(message = "Id is required!")
    Long id;
    @NotNull(message = "Status is required!")
    Integer status;
}
