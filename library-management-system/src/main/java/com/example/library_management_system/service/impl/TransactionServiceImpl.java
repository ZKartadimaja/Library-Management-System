package com.example.library_management_system.service.impl;

import com.example.library_management_system.repository.BookRepository;
import com.example.library_management_system.repository.PatronRepository;
import com.example.library_management_system.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class TransactionServiceImpl {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private PatronRepository patronRepository;

    @Autowired
    private BookRepository bookRepository;

    // Borrow Book
    //        GetBorrowTransactionResponse borrowTransaction = transactionService.saveBorrowTransaction(borrowDetails);
//        if (borrowTransaction != null) {
//            ApiResponse<Object> response = new ApiResponse<>(borrowTransaction, "Book borrowed successfully");
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        } else {
//            ApiResponse<Object> response = new ApiResponse<>(null, "Cannot borrow. Either the book is unavailable or the patron has reached their limit.");
//            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        // Return book
    //        GetReturnTransactionResponse returnTransaction = transactionService.saveBorrowTransaction(returnDetails);
//        if (returnTransaction != null) {
//            ApiResponse<Object> response = new ApiResponse<>(returnTransaction, "Book returned successfully");
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        } else {
//            ApiResponse<Object> response = new ApiResponse<>(null, "Cannot borrow. Either the book is unavailable or the patron has reached their limit.");
//            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//        }
}
