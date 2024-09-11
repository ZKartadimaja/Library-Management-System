package com.example.library_management_system.repository;

import com.example.library_management_system.dto.response.book.GetAllBookResponse;
import com.example.library_management_system.dto.response.book.GetOverdueBooks;
import com.example.library_management_system.entity.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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
    Page<GetAllBookResponse> findByTitleOrAuthor(@Param("keyword") String keyword, Pageable pageable);

    //Search Available Books
    @Query(
            value = "select * " +
                    "from books b " +
                    "where b.available_copies >= 0",
            nativeQuery = true
    )
    Page<GetAllBookResponse> findAvailableCopies(Pageable pageable);

    //Get Overdue Books
    @Query(
            value = "select * " +
                    "from transactions t " +
                    "where due_date -  borrowed date > 0",
            nativeQuery = true
    )
    Page<GetOverdueBooks> findOverdueBooks(Pageable pageable);

}
