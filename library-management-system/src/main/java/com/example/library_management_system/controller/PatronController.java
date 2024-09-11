package com.example.library_management_system.controller;

import com.example.library_management_system.entity.PatronEntity;
import com.example.library_management_system.service.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patrons")
public class PatronController {

    @Autowired
    private PatronService patronService;

    //  Register a new patron with name, email, and membership type.
    @PostMapping
    public ResponseEntity<PatronEntity> createPatron(@RequestBody PatronRequest patron) {
        return patronService.savePatron(patron);
    }

    //  Update the patron’s name, email, or membership type.
    @PutMapping("/{id}")
    public ResponseEntity<PatronEntity> updatePatron(@PathVariable Long id, @RequestBody PatronRequest patronDetails) {
        return new ResponseEntity<>(patronService.updatePatron(bookDetails));
    }

    //  Retrieve the borrowing history of a patron.
    @GetMapping("/{patron_id}/borrow_history")
    public ResponseEntity<PatronEntity> getPatronHistoryById(@PathVariable(name = "patron_id") Long patronId) {
        return new ResponseEntity<>(patronService.getPatronHistoryById(patronId));
    }

    //  Retrieve details of a specific patron, including current borrowed books.
    @GetMapping("/{patron_id}")
    public ResponseEntity<PatronEntity> getPatronsById(@PathVariable(name = "patron_id") Long patronId) {
        return new ResponseEntity<>(patronService.getPatronsById(patronId));
    }

    //  Deletes a patron from the system.
    @DeleteMapping("/{patron_id}")
    public ResponseEntity<PatronEntity> deletePatron(@PathVariable(name = "patron_id") Long patronId){
        return patronService.deletePatron(patronId);
    }
}
