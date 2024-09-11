package com.example.library_management_system.repository;

import com.example.library_management_system.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    // Query/Logic To Know How Many Patrons Borrow Book
    @Query(
            value = "select count(t) " +
                    "from transactions t " +
                    "where t.returned_date is null and t.patron_id = :patron_id",
            nativeQuery = true
    )
    int countTransactionByPatronId(@Param("patron_id") Long patronId);

    // Query To Get List Transaction Which Not Return by Patron
    @Query(
            value = "select t.* " +
                    "from transactions t " +
                    "where t.patron_id = :patron_id and t.book_id = :book_id and t.returned_date is null"
            , nativeQuery = true
    )
    List<TransactionEntity> findTransactionByPatronIdAndBookId(@Param("patron_id") Long patronId, @Param("book_id") Long bookId);

    // List All Transaction by A Patron
    @Query(
            value = "select * " +
                    "from transactions t " +
                    "where t.patron_id = :patron_id"
            , nativeQuery = true
    )
    List<TransactionEntity> findTransactionByPatronId(@Param("patron_id") Long patronId);

    //Query To Get List Transaction from One Book
    @Query(
            value = "select * " +
                    "from transactions t " +
                    "where t.book_id = :book_id"
            , nativeQuery = true
    )
    List<TransactionEntity> findTransactionByBookId(@Param("book_id") Long bookId);

}
