package com.example.library_management_system.repository;

import com.example.library_management_system.entity.BookEntity;
import com.example.library_management_system.entity.PatronEntity;
import com.example.library_management_system.entity.TransactionEntity;
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
                    "where b.title like %:keyword% or " +
                    "b.author like %:keyword% ",
            nativeQuery = true
    )
    List<BookEntity> findByTitleOrAuthor(@Param("keyword") String keyword);

    //Search Available Books
    @Query(
            value = "select * " +
                    "from books b " +
                    "where b.available_copies >= 0",
            nativeQuery = true
    )
    List<BookEntity> findAvailableCopies();


    //Get Overdue Books
    @Query(
            value = "select b.* " +
                    "from transactions t " +
                    "join books b on b.id = t.book_id " +
                    "join patrons p on p.id = t.patron_id " +
                    "where t.due_date - t.borrowed_date > 0",
            nativeQuery = true
    )
    List<BookEntity> findBookByOverdue();

    @Query(
            value = "select t.* " +
                    "from transactions t " +
                    "join books b on b.id = t.book_id " +
                    "join patrons p on p.id = t.patron_id " +
                    "where t.due_date - t.borrowed_date > 0",
            nativeQuery = true
    )
    List<TransactionEntity> findTransactionByOverdue();

    @Query(
            value = "select p.* " +
                    "from transactions t " +
                    "join books b on b.id = t.book_id " +
                    "join patrons p on p.id = t.patron_id " +
                    "where t.due_date - t.borrowed_date > 0",
            nativeQuery = true
    )
    List<PatronEntity> findPatronByOverdue();

    //Query to check if ISBN exists in the 'books' table
    @Query(
            value = "select case when count(b) > 0 then true else false end " +
                    "from books b " +
                    "where b.isbn = :isbn",
            nativeQuery = true
    )
    boolean existsByIsbn(@Param("isbn") String isbn);

}
