package com.orient.library.repository;

import com.orient.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> getBooksByIsDeleted(Integer isDelete);

    Book findBookByIdAndIsDeleted(Long id, Integer isDelete);
}
