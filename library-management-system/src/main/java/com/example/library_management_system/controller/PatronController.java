package com.example.library_management_system.controller;

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
    public ResponseEntity<ApiResponse<Object>> createPatron(@RequestBody PatronRequest patronDetails) {
        PatronResponse patron = patronService.savePatron(patronDetails);
        if (patron != null) {
            ApiResponse<Object> response = new ApiResponse<>(patron, "");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponse<Object> response = new ApiResponse<>(null, "Patron not found.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    //  Update the patron’s name, email, or membership type.
    @PutMapping("/{patron_id}")
    public ResponseEntity<ApiResponse<Object>> updatePatron(@PathVariable(name = "patron_id") Long patronId, @RequestBody PatronRequest patronDetails) {
        PatronResponse patron = patronService.updatePatron(patronId, patronDetails);
        if (patron != null) {
            ApiResponse<Object> response = new ApiResponse<>(null, "Patron details updated successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponse<Object> response = new ApiResponse<>(null, "Patron not found.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    //  Retrieve the borrowing history of a patron.
    @GetMapping("/{patron_id}/borrow_history")
    public ResponseEntity<ApiResponse<Object>> getPatronHistoryById(@PathVariable(name = "patron_id") Long patronId) {
        PatronResponse patron = patronService.getPatronHistoryById(patronId);
        if (patron != null) {
            ApiResponse<Object> response = new ApiResponse<>(patron, "");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponse<Object> response = new ApiResponse<>(null, "There is no borrowed history");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    //  Retrieve details of a specific patron, including current borrowed books.
    @GetMapping("/{patron_id}")
    public ResponseEntity<ApiResponse<Object>> getPatronsById(@PathVariable(name = "patron_id") Long patronId) {
        PatronResponse patron = patronService.getPatronsById(patronId);
        if (patron != null) {
            ApiResponse<Object> response = new ApiResponse<>(patron, "");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponse<Object> response = new ApiResponse<>(null, "There is no patron");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    //  Deletes a patron from the system.
    @DeleteMapping("/{patron_id}")
    public ResponseEntity<ApiResponse<Object>> deletePatron(@PathVariable(name = "patron_id") Long patronId){
        PatronResponse patron = patronService.deletePatron(patronId);
        if (patron != null) {
            ApiResponse<Object> response = new ApiResponse<>(null, "Patron deleted successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponse<Object> response = new ApiResponse<>(null, "Cannot delete patron with active loans.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
    //  Retrieve the list of books currently borrowed by a patron.
    @GetMapping("/{patron_id}/current_borrowings")
    public ResponseEntity<ApiResponse<Object>> getPatronCurrentBorrowingsById(@PathVariable(name = "patron_id") Long patronId) {
        PatronResponse patron = patronService.getPatronCurrentBorrowingsById(patronId);
        if (patron != null) {
            ApiResponse<Object> response = new ApiResponse<>(patron, "");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponse<Object> response = new ApiResponse<>(null, "There is no current borrow book.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
}
