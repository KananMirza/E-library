package com.orient.library.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PROTECTED)
public class PublishingRequestDto {
    Long id;
    @NotBlank(message = "Name is required!")
    String name;
    @NotBlank(message = "Description is required!")
    String description;
    @NotBlank(message = "Address is required!")
    String address;
}
