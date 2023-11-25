package com.orient.library.repository;

import com.orient.library.entity.User;
import com.orient.library.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findUserByEmailAndStatusAndIsDeleted(String email,Integer status,Integer isDeleted);
    List<User> getAllByIsDeletedAndUserRole_Id(Integer isDeleted, Long userRoleId);
    User findUserByIdAndIsDeletedAndUserRole_Id(Long id, Integer isDeleted, Long userRole);
}
