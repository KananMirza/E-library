package com.orient.library.mapper;

import com.orient.library.dto.request.UserRequestDto;
import com.orient.library.dto.response.UserResponseDto;
import com.orient.library.entity.User;
import com.orient.library.entity.UserRole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Mapper(componentModel = "user")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Mappings({
            @Mapping(target = "password", source = "password", qualifiedByName = "hashPassword"),
    })
    User dtoToEntity(UserRequestDto userRequestDto);

    UserResponseDto entityToDto(User user);

    @Named("hashPassword")
    default String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }


}
