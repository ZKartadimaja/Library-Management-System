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
<<<<<<< Updated upstream
    long countByPatronIdAndReturnDateIsNull(Long patronId);
=======
    @Query(
            value = "select t.* " +
                    "from transaction t " +
                    "where t.patron_id = :patronId and t.book_id = bookId"
            , nativeQuery = true
    )
    TransactionEntity findTransactionByPatronIdAndBookId(@Param("patron_id") Long patronId, @Param("book_id") Long bookId);

    @Query(
            value = "select b.* " +
                    "from books b " +
                    "join transactions t on t.book_id = b.id " +
                    "where b.id = :bookId",
            nativeQuery = true
    )
    BookEntity getBookById(@Param("book_id") Long bookId);
>>>>>>> Stashed changes
}
