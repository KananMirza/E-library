package com.orient.library.repository;

import com.orient.library.entity.PenaltyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PenaltyTypeRepository extends JpaRepository<PenaltyType,Long> {
    List<PenaltyType> getPenaltyTypeByIsDeleted(Integer isDelete);
    PenaltyType findPenaltyTypeByIdAndIsDeleted(Long id,Integer isDelete);
}
