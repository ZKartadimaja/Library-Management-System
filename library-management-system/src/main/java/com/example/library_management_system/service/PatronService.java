package com.example.library_management_system.service;

import com.example.library_management_system.dto.request.patron.CreatePatronRequest;
import com.example.library_management_system.dto.response.patron.CreateResponsePatron;
import com.example.library_management_system.dto.response.patron.GetBorrowingHistoryResponse;
import com.example.library_management_system.dto.response.patron.GetCurrentBorrowedResponse;
import com.example.library_management_system.dto.response.patron.GetPatronDetailsResponse;
import com.example.library_management_system.entity.PatronEntity;
import com.example.library_management_system.util.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface PatronService {

    ResponseEntity<ApiResponse<Object>> savePatron(CreatePatronRequest patron);
    ResponseEntity<ApiResponse<Object>> updatePatron(Long patronId, CreatePatronRequest patron);
    ResponseEntity<ApiResponse<Object>> getPatronHistoryById(Long patronId);
    ResponseEntity<ApiResponse<Object>> getPatronsById(Long patronId);
    ResponseEntity<ApiResponse<Object>> deletePatron(Long patronId);
    ResponseEntity<ApiResponse<Object>> getPatronCurrentBorrowingsById(Long patronId);

}
