package com.example.library_management_system.repository;

import com.example.library_management_system.entity.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
    //Get All Books
    Page<BookEntity> findAll(Pageable pageable);

    //Search Book by Author or Title
    @Query(
            value = "select * " +
                    "from books b " +
                    "where b.title like :%keyword% or " +
                    "b.author like :%keyword% ",
            nativeQuery = true
    )
    List<BookEntity> findByTitleOrAuthor(@Param("keyword") String keyword);
}
