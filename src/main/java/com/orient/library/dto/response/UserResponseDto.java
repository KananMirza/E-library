package com.orient.library.dto.response;

import com.orient.library.entity.UserPhone;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserResponseDto {
    Long id;
    Long userRoleId;
    String firstName;
    String lastName;
    List<UserPhone> phoneList;
    String patryonomic;
    String email;
    String seriaCode;
    Integer seriaNumber;
    String fin;
    Integer status;
    LocalDateTime createdAt;
}
