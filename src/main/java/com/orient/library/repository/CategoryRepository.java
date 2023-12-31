package com.orient.library.repository;

import com.orient.library.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    List<Category> getCategoriesByIsDeleted(Integer isDeleted);
    List<Category> findAllByIdInAndIsDeletedAndStatus(List<Long> ids,Integer isDelete,Integer status);
    Category findCategoryByIdAndIsDeleted(Long id, Integer isDeleted);
}
