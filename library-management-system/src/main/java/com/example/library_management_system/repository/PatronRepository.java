package com.example.library_management_system.repository;

import com.example.library_management_system.entity.PatronEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatronRepository extends JpaRepository<PatronEntity, Long>{

}
