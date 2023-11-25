package com.orient.library.service.impl;

import com.orient.library.dto.request.StatusRequestDto;
import com.orient.library.dto.response.UserResponseDto;
import com.orient.library.entity.User;
import com.orient.library.enums.DeleteType;
import com.orient.library.enums.Message;
import com.orient.library.enums.UserRoles;
import com.orient.library.exception.DataNotFoundException;
import com.orient.library.mapper.UserMapper;
import com.orient.library.repository.UserRepository;
import com.orient.library.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<UserResponseDto> getAllUserList() {
        List<UserResponseDto> userList = userRepository.getAllByIsDeletedAndUserRole_Id(DeleteType.NONDELETE.value()
                        , UserRoles.USER.value()).stream()
                .map(UserMapper.INSTANCE::entityToDto).collect(Collectors.toList());
        return userList;
    }

    @Override
    public UserResponseDto findUserById(Long userId) {
        return UserMapper.INSTANCE.entityToDto(findUser(userId));
    }

    @Override
    public String changeStatus(StatusRequestDto statusRequestDto) {
        User user = findUser(statusRequestDto.getId());
        user.setStatus(statusRequestDto.getStatus());
        userRepository.save(user);
        return "User status has been successfully updated!";
    }

    public User findUser(Long userId) {
        User user = userRepository.findUserByIdAndIsDeletedAndUserRole_Id(userId, DeleteType.NONDELETE.value()
        ,UserRoles.USER.value());
        if (user == null) {
            throw new DataNotFoundException(Message.USER_NOT_FOUND.value());
        }
        return user;
    }
}
