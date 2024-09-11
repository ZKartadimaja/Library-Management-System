package com.example.library_management_system.controller;

import com.example.library_management_system.dto.request.patron.CreatePatronRequest;
import com.example.library_management_system.dto.response.book.CreateBookResponse;
import com.example.library_management_system.dto.response.patron.CreateResponsePatron;
import com.example.library_management_system.dto.response.patron.GetBorrowingHistoryResponse;
import com.example.library_management_system.dto.response.patron.GetCurrentBorrowedResponse;
import com.example.library_management_system.dto.response.patron.GetPatronDetailsResponse;
import com.example.library_management_system.entity.PatronEntity;
import com.example.library_management_system.service.PatronService;
import com.example.library_management_system.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patrons")
public class PatronController {

    @Autowired
    private PatronService patronService;

    //  Register a new patron with name, email, and membership type.
    @PostMapping
    public ResponseEntity<ApiResponse<Object>> createPatron(@RequestBody CreatePatronRequest patronDetails) {
        return  patronService.savePatron(patronDetails);
    }

    //  Update the patron’s name, email, or membership type.
    @PutMapping("/{patron_id}")
    public ResponseEntity<ApiResponse<Object>> updatePatron(@PathVariable(name = "patron_id") Long patronId, @RequestBody CreatePatronRequest patronDetails) {
        return patronService.updatePatron(patronId, patronDetails);
    }

    //  Retrieve the borrowing history of a patron.
    @GetMapping("/{patron_id}/borrow_history")
    public ResponseEntity<ApiResponse<Object>> getPatronHistoryById(@PathVariable(name = "patron_id") Long patronId) {
        return patronService.getPatronHistoryById(patronId);
    }

    //  Retrieve details of a specific patron, including current borrowed books.
    @GetMapping("/{patron_id}")
    public ResponseEntity<ApiResponse<Object>> getPatronsById(@PathVariable(name = "patron_id") Long patronId) {
        return patronService.getPatronsById(patronId);
    }

    //  Deletes a patron from the system.
    @DeleteMapping("/{patron_id}")
    public ResponseEntity<ApiResponse<Object>> deletePatron(@PathVariable(name = "patron_id") Long patronId){
        return patronService.deletePatron(patronId);
    }
    //  Retrieve the list of books currently borrowed by a patron.
    @GetMapping("/{patron_id}/current_borrowings")
    public ResponseEntity<ApiResponse<Object>> getPatronCurrentBorrowingsById(@PathVariable(name = "patron_id") Long patronId) {
        return patronService.getPatronCurrentBorrowingsById(patronId);
    }
}
