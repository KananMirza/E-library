package com.orient.library.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FileResponseDto {
    Long id;
    String fileContent;
    String key;
}