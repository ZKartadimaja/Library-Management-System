package com.example.library_management_system.service.impl;

import com.example.library_management_system.dto.request.patron.CreatePatronRequest;
import com.example.library_management_system.dto.response.patron.*;
import com.example.library_management_system.entity.BookEntity;
import com.example.library_management_system.entity.PatronEntity;
import com.example.library_management_system.entity.TransactionEntity;
import com.example.library_management_system.repository.PatronRepository;
import com.example.library_management_system.service.PatronService;
import com.example.library_management_system.util.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class PatronServiceImpl implements PatronService {

    @Autowired
    private PatronRepository patronRepository;

    public ResponseEntity<ApiResponse<Object>> savePatron(CreatePatronRequest patron){
        if(patron.getName() == null || patron.getEmail() == null || patron.getMembershipType() == null){
            ApiResponse<Object> response = new ApiResponse<>(null, "Patron not found.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        Pattern pattern = Pattern.compile("^.+@.+\\..+$");
        Matcher matcher = pattern.matcher(patron.getEmail());
        if(!matcher.matches() || (!patron.getMembershipType().equals("regular") && !patron.getMembershipType().equals("premium"))) {
            ApiResponse<Object> response = new ApiResponse<>(null, "Patron not found.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        PatronEntity patronEntity = new PatronEntity();
        patronEntity.setName(patron.getName());
        patronEntity.setEmail(patron.getEmail());
        patronEntity.setMembershipType(patron.getMembershipType());
        patronEntity.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        PatronEntity result = patronRepository.save(patronEntity);


        ApiResponse<Object> response = new ApiResponse<>(CreateResponsePatron.builder()
                .id(result.getId())
                .name(result.getName())
                .membershipType(result.getMembershipType())
                .build()
                ,"");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<ApiResponse<Object>> updatePatron(Long patronId, CreatePatronRequest patron){
        if(patron.getName() == null || patron.getEmail() == null){
            ApiResponse<Object> response = new ApiResponse<>(null, "Patron not found.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        PatronEntity checkPatron = patronRepository.findById(patronId).orElse(null);
        Pattern pattern = Pattern.compile("^.+@.+\\..+$");
        Matcher matcher = pattern.matcher(patron.getEmail());
        if(checkPatron == null || patron.getMembershipType() == null || !matcher.matches() || (!patron.getMembershipType().equals("regular") && !patron.getMembershipType().equals("premium"))) {
            ApiResponse<Object> response = new ApiResponse<>(null, "Patron not found.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        checkPatron.setName(patron.getName());
        checkPatron.setEmail(patron.getEmail());
        checkPatron.setMembershipType(patron.getMembershipType());
        checkPatron.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        PatronEntity result = patronRepository.save(checkPatron);

        ApiResponse<Object> response = new ApiResponse<>(null, "Patron details updated successfully.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<ApiResponse<Object>> getPatronHistoryById(Long patronId){
        Optional<PatronEntity> result = patronRepository.findById(patronId);
        if (result.isPresent()) {
            List<BookEntity> books = patronRepository.findBookByPatronId(patronId);
            List<TransactionEntity> transactions = patronRepository.findTransactionByPatronId(patronId);
            List<GetBorrowingHistoryResponse> allBook = new ArrayList<>();
            for(int i=0; i<books.size();i++){
                if(transactions.get(i).getReturnedDate() == null){
                    allBook.add(GetBorrowingHistoryResponse.builder().title(books.get(i).getTitle()).borrowedDate(transactions.get(i).getBorrowedDate()).returnedDate(transactions.get(i).getReturnedDate()).fine(transactions.get(i).getFine()).build());
                }
            }
            ApiResponse<Object> response = new ApiResponse<>(allBook, "");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponse<Object> response = new ApiResponse<>(null, "There is no borrowed history");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    public ResponseEntity<ApiResponse<Object>> getPatronsById(Long patronId) {
        PatronEntity result = patronRepository.findById(patronId).orElse(null);
        if (result != null) {
            List<BookEntity> books = patronRepository.findBookByPatronId(patronId);
            List<TransactionEntity> transactions = patronRepository.findTransactionByPatronId(patronId);
            List<GetBorrowBookDetailsResponse> allBook = new ArrayList<>();
            for(int i=0; i<books.size();i++){
                if(transactions.get(i).getReturnedDate() != null){
                    allBook.add(GetBorrowBookDetailsResponse.builder().bookId(books.get(i).getId()).title(books.get(i).getTitle()).dueDate(transactions.get(i).getDueDate()).build());
                }
            }
            ApiResponse<Object> response = new ApiResponse<>(GetPatronDetailsResponse.builder().id(result.getId()).name(result.getName()).email(result.getEmail()).membershipType(result.getMembershipType()).borrowedBooks(allBook).build(), "");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponse<Object> response = new ApiResponse<>(null, "There is no patron");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<ApiResponse<Object>> deletePatron(Long patronId) {
        PatronEntity patron = patronRepository.findById(patronId).orElse(null);
        if(patron != null) {
            List<TransactionEntity> transactions = patronRepository.findTransactionByPatronId(patronId);
            if (transactions == null){
                patronRepository.delete(patron);
                ApiResponse<Object> response = new ApiResponse<>(null, "Patron deleted successfully.");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            for (TransactionEntity transaction : transactions) {
                if (transaction.getReturnedDate() == null) {
                    ApiResponse<Object> response = new ApiResponse<>(null, "Cannot delete patron with active loans.");
                    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                }
            }
            patronRepository.delete(patron);
            ApiResponse<Object> response = new ApiResponse<>(null, "Patron deleted successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponse<Object> response = new ApiResponse<>(null, "Cannot delete patron with active loans.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<ApiResponse<Object>> getPatronCurrentBorrowingsById(Long patronId){
        PatronEntity result = patronRepository.findById(patronId).orElse(null);
        if (result != null) {
            List<BookEntity> books = patronRepository.findBookByPatronId(patronId);
            List<TransactionEntity> transactions = patronRepository.findTransactionByPatronId(patronId);
            List<GetCurrentBorrowedResponse> allBook = new ArrayList<>();
            for(int i=0; i<books.size();i++){
                if(transactions.get(i).getReturnedDate() == null){
                    allBook.add(GetCurrentBorrowedResponse.builder().title(books.get(i).getTitle()).borrowedDate(transactions.get(i).getBorrowedDate()).dueDate(transactions.get(i).getDueDate()).build());
                }
            }
            ApiResponse<Object> response = new ApiResponse<>(allBook, "");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponse<Object> response = new ApiResponse<>(null, "There is no current borrow book.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
}
