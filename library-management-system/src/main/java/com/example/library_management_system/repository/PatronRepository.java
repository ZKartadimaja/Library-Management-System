package com.example.library_management_system.repository;

import com.example.library_management_system.entity.BookEntity;
import com.example.library_management_system.entity.PatronEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatronRepository extends JpaRepository<PatronEntity, Long>{

    @Query(
            value = "select b.* " +
                    "from patrons p " +
                    "join transactions t on t.patron_id = p.id " +
                    "join books b on t.book_id = b.id " +
                    "where p.id = :id",
            nativeQuery = true
    )
    List<BookEntity> findBookByPatronId(@Param("id") Long id);

}
