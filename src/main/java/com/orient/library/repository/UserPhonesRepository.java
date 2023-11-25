package com.orient.library.repository;

import com.orient.library.entity.UserPhone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPhonesRepository extends JpaRepository<UserPhone,Long> {
}
