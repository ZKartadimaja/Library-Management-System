package com.example.library_management_system.controller;

import com.example.library_management_system.entity.PatronEntity;
import com.example.library_management_system.service.PatronService;
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
    public ResponseEntity<PatronResponse> createPatron(@RequestBody PatronRequest patronDetails) {
        PatronResponse patron = patronService.savePatron(patronDetails);
        if (patron != null) {
            return ResponseEntity.ok(patron);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //  Update the patron’s name, email, or membership type.
    @PutMapping("/{patron_id}")
    public ResponseEntity<PatronResponse> updatePatron(@PathVariable(name = "patron_id") Long patronId, @RequestBody PatronRequest patronDetails) {
        PatronResponse patron = patronService.updatePatron(patronId, patronDetails);
        if (patron != null) {
            return ResponseEntity.ok(patron);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //  Retrieve the borrowing history of a patron.
    @GetMapping("/{patron_id}/borrow_history")
    public ResponseEntity<PatronResponse> getPatronHistoryById(@PathVariable(name = "patron_id") Long patronId) {
        PatronResponse patron = patronService.getPatronHistoryById(patronId);
        if (patron != null) {
            return ResponseEntity.ok(patron);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //  Retrieve details of a specific patron, including current borrowed books.
    @GetMapping("/{patron_id}")
    public ResponseEntity<PatronResponse> getPatronsById(@PathVariable(name = "patron_id") Long patronId) {
        PatronResponse patron = patronService.getPatronsById(patronId);
        if (patron != null) {
            return ResponseEntity.ok(patron);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //  Deletes a patron from the system.
    @DeleteMapping("/{patron_id}")
    public ResponseEntity<PatronResponse> deletePatron(@PathVariable(name = "patron_id") Long patronId){
        PatronResponse patron = patronService.deletePatron(patronId);
        if (patron != null) {
            return ResponseEntity.ok(patron);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    //  Retrieve the list of books currently borrowed by a patron.
    @GetMapping("/{patron_id}/current_borrowings")
    public ResponseEntity<PatronResponse> getPatronCurrentBorrowingsById(@PathVariable(name = "patron_id") Long patronId) {
        PatronResponse patron = patronService.getPatronCurrentBorrowingsById(patronId);
        if (patron != null) {
            return ResponseEntity.ok(patron);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
