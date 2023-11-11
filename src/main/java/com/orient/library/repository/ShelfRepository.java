package com.orient.library.repository;

import com.orient.library.entity.Shelf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShelfRepository extends JpaRepository<Shelf,Long> {
    List<Shelf> getShelvesByIsDeleted(Integer isDelete);
    Shelf findShelfByIdAndIsDeleted(Long id,Integer isDelete);
}
