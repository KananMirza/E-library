package com.orient.library.dto.response;

import com.orient.library.entity.UserPhone;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserResponseDto {
    Long id;
    String firstName;
    String lastName;
    List<UserPhone> phones;
    String patryonomic;
    String email;
    String seriaCode;
    Integer seriaNumber;
    String fin;
    Integer status;
    LocalDateTime createdAt;
}
