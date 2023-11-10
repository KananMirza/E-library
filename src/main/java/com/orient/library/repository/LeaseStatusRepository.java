package com.orient.library.repository;

import com.orient.library.entity.LeaseStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaseStatusRepository extends JpaRepository<LeaseStatus,Long> {
    List<LeaseStatus> getLeaseStatusesByIsDeleted(Integer isDelete);
    LeaseStatus findByIdAndIsDeleted(Long id,Integer isDelete);
}
