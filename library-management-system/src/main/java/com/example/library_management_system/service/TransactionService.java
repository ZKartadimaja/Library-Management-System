package com.example.library_management_system.service;

import com.example.library_management_system.dto.request.transaction.CreateBorrowReturnRequest;
import com.example.library_management_system.util.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface TransactionService {
    // Borrow A Book
    ResponseEntity<ApiResponse<Object>> borrowBook(CreateBorrowReturnRequest borrowDetails);

    // Return A Book
    ResponseEntity<ApiResponse<Object>> returnBook(CreateBorrowReturnRequest returnDetails);
}
