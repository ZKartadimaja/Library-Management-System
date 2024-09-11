package com.example.library_management_system.controller;


import com.example.library_management_system.entity.BookEntity;
import com.example.library_management_system.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    // List All Available Books - Retrieve a list of all available books in the library.
    @GetMapping()
    public Page<BookResponse> getAllBooks(Pageable pageable) {
        return bookService.getAllBooks(pageable);
    }

    // Search Books by Title or Author - Search for books by title or author.
    @GetMapping("/search")
    public ResponseEntity<Page<BookResponse>> getBooksByKeyword(
            @RequestParam (name = "keyword", required = true) String keyword,
            Pageable pageable) {

        Page<BookResponse> books = bookService.getBooksByKeyword(keyword, pageable);
        if (books.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(books);
    }

    // Get Overdue Books - Retrieve a list of overdue books and their patrons.
    @GetMapping("/overdue")
    public ResponseEntity<Page<BookResponse>> getOverdueBooks(Pageable pageable) {
        Page<BookResponse> overdueBooks = bookService.getOverdueBooks(pageable);
        if (overdueBooks.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(overdueBooks);
    }

    // Check Available Copies of a Book - Check how many copies of a book are available for borrowing.
    @GetMapping("/{book_id}/availability")
    public ResponseEntity<BookResponse> getAvailableBookCopiesById(
            @PathVariable Long id
    ){
        BookResponse book = bookService.getAvailableBookCopiesById(id);
        if (book != null) {
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    

}
