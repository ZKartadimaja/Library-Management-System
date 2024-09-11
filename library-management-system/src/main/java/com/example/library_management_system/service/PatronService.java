package com.example.library_management_system.service;

import org.springframework.stereotype.Component;

@Component
public interface PatronService {

    PatronResponse savePatron(PatronRequest patron);
    PatronResponse updatePatron(Long patronId, PatronRequest patron);
    PatronResponse getPatronHistoryById(Long patronId);
    PatronResponse getPatronsById(Long patronId);
    PatronResponse deletePatron(Long patronId);
    PatronResponse getPatronCurrentBorrowingsById(Long patronId);

}
