package com.orient.library.repository;

import com.orient.library.entity.Publishing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublishingRepository extends JpaRepository<Publishing,Long> {
    List<Publishing> getPublishingsByIsDeleted(Integer isDeleted);
    Publishing findPublishingByIdAndIsDeleted(Long id,Integer isDeleted);
}
