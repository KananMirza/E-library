package com.orient.library.repository;

import com.orient.library.entity.Lease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaseRepository extends JpaRepository<Lease,Long> {
    Lease findLeaseById(Long id);
}
