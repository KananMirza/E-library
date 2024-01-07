package com.orient.library.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ImageRequestDto {
    @NotNull(message = "FileType is required!")
    String fileType;
    @NotNull(message = "FileContent is required!")
    String fileBase64;
}
