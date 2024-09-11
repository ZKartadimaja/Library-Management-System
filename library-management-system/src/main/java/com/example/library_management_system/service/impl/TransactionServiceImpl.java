package com.example.library_management_system.service.impl;

import com.example.library_management_system.dto.request.transaction.CreateBorrowReturnRequest;
import com.example.library_management_system.entity.BookEntity;
import com.example.library_management_system.entity.PatronEntity;
import com.example.library_management_system.entity.TransactionEntity;
import com.example.library_management_system.repository.BookRepository;
import com.example.library_management_system.repository.PatronRepository;
import com.example.library_management_system.repository.TransactionRepository;
import com.example.library_management_system.service.TransactionService;
import com.example.library_management_system.util.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private PatronRepository patronRepository;

    private BookRepository bookRepository;

    private TransactionRepository transactionRepository;

    @Override
    public ResponseEntity<ApiResponse<Object>> borrowBook(CreateBorrowReturnRequest borrowDetails) {

        Long patronId = borrowDetails.getPatronId();
        Long bookId = borrowDetails.getBookId();

        //Validate patron_id and book_id existence
        boolean checkPatronId = patronRepository.existsById(patronId);
        boolean checkBookId = bookRepository.existsById(bookId);

        if (!checkPatronId || !checkBookId) {
            ApiResponse<Object> response = new ApiResponse<>(null, "Invalid patron ID or book ID.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Optional<PatronEntity> patronOpt = patronRepository.findById(patronId);
        Optional<BookEntity> bookOpt = bookRepository.findById(bookId);

        PatronEntity patron = patronOpt.get();
        BookEntity book = bookOpt.get();

        //Check patron borrowing limit based on membership type and available copies of the book
        int borrowingLimit = patron.getMembershipType().equals("Premium") ? 10 : 5;
        long borrowedCount = transactionRepository.countByPatronIdAndReturnDateIsNull(patronId);

        if (borrowedCount >= borrowingLimit || book.getAvailableCopies() <= 0) {
            ApiResponse<Object> response = new ApiResponse<>(null, "Cannot borrow. Either the book is unavailable or the patron has reached their limit.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        //Create a new transaction
        TransactionEntity transaction = new TransactionEntity();
        transaction.setPatronId(patron);
        transaction.setBookId(book);
        transaction.setBorrowedDate(Date.valueOf(LocalDate.now()));
        transaction.setDueDate(Date.valueOf(LocalDate.now().plusWeeks(1)));

        //Update available copies of the book
        book.setAvailableCopies(book.getAvailableCopies() - 1);

        //Save the transaction and updated book
        transactionRepository.save(transaction);
        bookRepository.save(book);

        ApiResponse<Object> response = new ApiResponse<>(transaction.getDueDate(), "Book borrowed successfully.");
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
