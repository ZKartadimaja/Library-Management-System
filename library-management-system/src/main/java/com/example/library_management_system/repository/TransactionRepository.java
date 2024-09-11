package com.example.library_management_system.repository;

import com.example.library_management_system.entity.BookEntity;
import com.example.library_management_system.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    @Query(
            value = "select count(t) " +
                    "from transactions t " +
                    "where t.returned_date is null and t.patron_id = :patron_id",
            nativeQuery = true
    )
    int countTransactionByPatronId(@Param("patron_id") Long patronId);

    @Query(
            value = "select t.* " +
                    "from transactions t " +
                    "where t.patron_id = :patron_id and t.book_id = :book_id"
            , nativeQuery = true
    )
    TransactionEntity findTransactionByPatronIdAndBookId(@Param("patron_id") Long patronId, @Param("book_id") Long bookId);

    @Query(
            value = "select b " +
                    "from books b " +
                    "where b.id = :book_id",
            nativeQuery = true
    )
    BookEntity getBookById(@Param("book_id") Long bookId);
}
