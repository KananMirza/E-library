package com.orient.library.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShelfRequestDto {
    Long id;
    @NotNull
    @Size(max = 25,min = 3,message = "Shelf No must be between 3 and 25")
    String shelfNo;

}
