package com.orient.library.repository;

import com.orient.library.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> getAuthorsByIsDeleted(Integer isDelete);
    List<Author> findAllByIdInAndIsDeletedAndStatus(List<Long> ids,Integer isDelete,Integer status);
    Author findAuthorByIdAndIsDeleted(Long id,Integer isDelete);
}
