package com.example.library_management_system.controller;

import com.example.library_management_system.dto.request.transaction.CreateBorrowReturnRequest;
import com.example.library_management_system.service.TransactionService;
import com.example.library_management_system.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    // Borrow A Book
    @PostMapping
    @RequestMapping("/api/borrow")
    public ResponseEntity<ApiResponse<Object>> borrowBook(@RequestBody CreateBorrowReturnRequest borrowDetails) {
        return transactionService.borrowBook(borrowDetails);
        }

    // Return A Book
    @PostMapping
    @RequestMapping("/api/return")
    public ResponseEntity<ApiResponse<Object>> returnBook(@RequestBody CreateBorrowReturnRequest returnDetails) {
        return transactionService.returnBook(returnDetails);
    }
}
