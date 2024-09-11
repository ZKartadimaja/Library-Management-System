package com.example.library_management_system.controller;

import com.example.library_management_system.dto.request.book.CreateBookRequest;
import com.example.library_management_system.dto.request.book.UpdateBookRequest;
import com.example.library_management_system.dto.response.book.GetAllBookResponse;
import com.example.library_management_system.dto.response.book.GetOverdueBooks;
import com.example.library_management_system.service.BookService;
import com.example.library_management_system.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    // Add a New Book
    @PostMapping
    public ResponseEntity<ApiResponse<Object>> createBook(@RequestBody CreateBookRequest bookDetails) {
        return bookService.saveBook(bookDetails);
    }

    // Update Book Details
    @PutMapping("/{book_id}")
    public ResponseEntity<ApiResponse<Object>> updateBook(@PathVariable(name = "book_id") Long bookId, @RequestBody UpdateBookRequest bookDetails) {
        return bookService.updateBook(bookId, bookDetails);
    }

    // List All Available Books
    @GetMapping()
    public List<GetAllBookResponse> getAllAvailableBooks() {
        return bookService.getAllAvailableBooks();
    }

    // Search Books by Title or Author
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<Object>> getBooksByKeyword(@RequestParam (name = "keyword", required = true) String keyword, Pageable pageable) {
        return bookService.getBooksByKeyword(keyword, pageable);
        }

    // Get Overdue Books - Retrieve a list of overdue books and their patrons.
    @GetMapping("/overdue")
    public List<GetOverdueBooks> getOverdueBooks() {
        return bookService.getOverdueBooks();
        }

    // Check Available Copies of a Book
    @GetMapping("/{book_id}/availability")
    public ResponseEntity<ApiResponse<Object>> getAvailableBookCopiesById(@PathVariable(name = "book_id") Long bookId) {
        return bookService.getAvailableBookCopiesById(bookId);
    }

    // Delete a Book
    @DeleteMapping("/{book_id}")
    public ResponseEntity<ApiResponse<Object>> deleteBook (@PathVariable(name = "book_id") Long bookId) {
        return bookService.deleteBook(bookId);
    }

}
