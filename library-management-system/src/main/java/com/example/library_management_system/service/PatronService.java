package com.example.library_management_system.service;

import com.example.library_management_system.dto.request.patron.CreatePatronRequest;
import com.example.library_management_system.util.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface PatronService {

    // Save New Patron
    ResponseEntity<ApiResponse<Object>> savePatron(CreatePatronRequest patron);

    // Update Patron
    ResponseEntity<ApiResponse<Object>> updatePatron(Long patronId, CreatePatronRequest patron);

    // Get List Book Transaction By A Patron
    ResponseEntity<ApiResponse<Object>> getPatronHistoryById(Long patronId);

    // Get Details of A Patron
    ResponseEntity<ApiResponse<Object>> getPatronsById(Long patronId);

    // Delete Patron
    ResponseEntity<ApiResponse<Object>> deletePatron(Long patronId);

    // Lastest Book Borrowed By A Patron
    ResponseEntity<ApiResponse<Object>> getPatronCurrentBorrowingsById(Long patronId);

}
