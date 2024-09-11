package com.example.library_management_system.controller;

import com.example.library_management_system.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    // Add a New Book
    @PostMapping
    public ResponseEntity<ApiResponse> createBook(@RequestBody CreateBookRequest book) {
        return bookService.saveBook(book);
    }

    // Update Book Details
    @PutMapping("/{book_id}")
    public ResponseEntity<ApiResponse> updateBook(@PathVariable(name = "book_id") Long bookId, @RequestBody UpdateBookRequest bookDetails) {
        return bookService.updateBook(bookDetails);
    }

    // List All Available Books
    @GetMapping()
    public Page<ApiResponse> getAllAvailableBooks(Pageable pageable) {
        return bookService.getAllAvailableBooks(pageable);
    }

    // Search Books by Title or Author
    @GetMapping("/search")
    public ResponseEntity<Page<ApiResponse>> getBooksByKeyword(@RequestParam (name = "keyword", required = true) String keyword, Pageable pageable) {
        return bookService.getBooksByKeyword(keyword, pageable);
    }

    // Get Overdue Books - Retrieve a list of overdue books and their patrons.
    @GetMapping("/overdue")
    public ResponseEntity<Page<ApiResponse>> getOverdueBooks(Pageable pageable) {
        return bookService.getOverdueBooks(pageable);
    }

    // Check Available Copies of a Book
    @GetMapping("/{book_id}/availability")
    public ResponseEntity<ApiResponse> getAvailableBookCopiesById(@PathVariable(name = "book_id") Long bookId) {
        return bookService.getAvailableBookCopiesById(bookId);
    }

    // Delete a Book
    @DeleteMapping("/{book_id}")
    public ResponseEntity<ApiResponse> deleteBook (@PathVariable(name = "book_id") Long bookId) {
        return bookService.deleteBook(bookId);
    }

}
