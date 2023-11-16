package com.orient.library.repository;

import com.orient.library.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findUserByEmailAndStatusAndIsDeleted(String email,Integer status,Integer isDeleted);
}
