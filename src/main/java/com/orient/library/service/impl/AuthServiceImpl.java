package com.orient.library.service.impl;

import com.orient.library.dto.request.UserRequestDto;
import com.orient.library.entity.User;
import com.orient.library.entity.UserPhone;
import com.orient.library.entity.UserRole;
import com.orient.library.enums.Message;
import com.orient.library.exception.DataNotFoundException;
import com.orient.library.mapper.UserMapper;
import com.orient.library.repository.UserPhonesRepository;
import com.orient.library.repository.UserRepository;
import com.orient.library.repository.UserRoleRepository;
import com.orient.library.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserPhonesRepository userPhonesRepository;
    @Override
    public String register(UserRequestDto registerRequestDto) {
        User user = UserMapper.INSTANCE.dtoToEntity(registerRequestDto);
        if(registerRequestDto.getUserRoleId() != null){
            UserRole userRole = userRoleRepository.findUserRoleById(registerRequestDto.getUserRoleId());
            if(userRole == null){
                throw new DataNotFoundException(Message.USER_ROLE_NOT_FOUND.value());
            }
            user.setUserRole(userRole);
        }
        List<String> phoneList = registerRequestDto.getPhoneList();
        List<UserPhone> savedUserPhoneList = new ArrayList<>();
        User savedUser = userRepository.save(user);
        for(String phone : phoneList){
            UserPhone userPhone = new UserPhone();
            userPhone.setUser(savedUser);
            userPhone.setPhone(phone);
            savedUserPhoneList.add(userPhone);
        }
        userPhonesRepository.saveAll(savedUserPhoneList);
        return "User has been successfully registered!";
    }
}
