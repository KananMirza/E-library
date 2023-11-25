package com.orient.library.dto.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class UserRequestDto {
    @Nullable
    Long userRoleId;
    @NotBlank(message = "First name is required!")
    String firstName;
    @NotBlank(message = "Last name is required!")
    String lastName;
    @NotBlank(message = "Patryonomic is required!")
    String patryonomic;
    @NotBlank(message = "Email is required!")
    String email;
    @NotBlank(message = "Password is required!")
    String password;
    @NotBlank(message = "Seria code is required!")
    String seriaCode;
    @NotNull(message = "Name is required!")
    Integer seriaNumber;
    @NotBlank(message = "Fin is required!")
    @Size(min = 7, max = 7, message = "Fin must have exactly 7 characters!")
    String fin;

    @Nullable
    List<String> phoneList;
}
