package com.orient.library.auth;

import com.orient.library.entity.User;
import com.orient.library.enums.DeleteType;
import com.orient.library.enums.Status;
import com.orient.library.exception.DataNotFoundException;
import com.orient.library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class UserDetailService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmailAndStatusAndIsDeleted(userEmail, Status.ACTIVE.value(), DeleteType.NONDELETE.value());
        if(user != null){
            return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),new ArrayList<>());
        }
        throw new DataNotFoundException("User not found!");
    }
}
