package com.example.library_management_system.controller;

import com.example.library_management_system.dto.request.transaction.CreateBorrowReturnRequest;
import com.example.library_management_system.dto.response.transaction.GetBorrowTransactionResponse;
import com.example.library_management_system.dto.response.transaction.GetReturnTransactionResponse;
import com.example.library_management_system.service.TransactionService;
import com.example.library_management_system.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
//        GetBorrowTransactionResponse borrowTransaction = transactionService.saveBorrowTransaction(borrowDetails);
//        if (borrowTransaction != null) {
//            ApiResponse<Object> response = new ApiResponse<>(borrowTransaction, "Book borrowed successfully");
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        } else {
//            ApiResponse<Object> response = new ApiResponse<>(null, "Cannot borrow. Either the book is unavailable or the patron has reached their limit.");
//            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    // Return A Book
    @PostMapping
    @RequestMapping("/api/return")
    public ResponseEntity<ApiResponse<Object>> returnBook(@RequestBody CreateBorrowReturnRequest returnDetails) {
        return transactionService.returnBook(returnDetails);
//        GetReturnTransactionResponse returnTransaction = transactionService.saveBorrowTransaction(returnDetails);
//        if (returnTransaction != null) {
//            ApiResponse<Object> response = new ApiResponse<>(returnTransaction, "Book returned successfully");
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        } else {
//            ApiResponse<Object> response = new ApiResponse<>(null, "Cannot borrow. Either the book is unavailable or the patron has reached their limit.");
//            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//        }
    }
}
