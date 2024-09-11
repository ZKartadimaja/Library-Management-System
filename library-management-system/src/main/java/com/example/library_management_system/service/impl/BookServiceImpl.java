package com.example.library_management_system.service.impl;

import com.example.library_management_system.dto.request.book.CreateBookRequest;
import com.example.library_management_system.dto.request.book.UpdateBookRequest;
import com.example.library_management_system.dto.response.book.GetAllBookResponse;
import com.example.library_management_system.dto.response.book.GetOverdueBooks;
import com.example.library_management_system.entity.BookEntity;
import com.example.library_management_system.repository.BookRepository;
import com.example.library_management_system.service.BookService;
import com.example.library_management_system.util.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    // Add a New Book
    @Override
    public ResponseEntity<ApiResponse<Object>> saveBook(CreateBookRequest bookDetails) {
        if (bookDetails.getTitle() == null || bookDetails.getAuthor() == null || bookDetails.getIsbn() == null || bookDetails.getQuantity() <= 0) {
            ApiResponse<Object> response = new ApiResponse<>(null, "Invalid input. Ensure all fields are filled and ISBN is unique.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        BookEntity newBook = new BookEntity();
        newBook.setTitle(bookDetails.getTitle());
        newBook.setAuthor(bookDetails.getAuthor());
        newBook.setIsbn(bookDetails.getIsbn());
        newBook.setQuantity(bookDetails.getQuantity());
        newBook.setAvailableCopies(bookDetails.getQuantity());

        BookEntity savedBook = bookRepository.save(newBook);

        ApiResponse<Object> response = new ApiResponse<>(savedBook, "");
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    // Update Book Details
    @Override
    public ResponseEntity<ApiResponse<Object>> updateBook(Long bookId, UpdateBookRequest bookDetails) {
        BookEntity book = bookRepository.findById(bookId)
                .orElse(null);
        if (bookDetails.getTitle() == null || bookDetails.getAuthor() == null || bookDetails.getQuantity() <= 0) {
            ApiResponse<Object> response = new ApiResponse<>(null, "Invalid input. Ensure all fields are filled.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setQuantity(bookDetails.getQuantity());
        book.setAvailableCopies(bookDetails.getQuantity());

        BookEntity updatedBook = bookRepository.save(book);

        ApiResponse<Object> response = new ApiResponse<>(updatedBook, "");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Get All Available Books
    @Override
    public Page<GetAllBookResponse> getAllAvailableBooks(Pageable pageable) {
        return bookRepository.findAvailableCopies(pageable);
    }

    // Search Books by Title or Author
    @Override
    public ResponseEntity<ApiResponse<Object>> getBooksByKeyword(String keyword, Pageable pageable) {
        if (keyword == null) {
            ApiResponse<Object> response = new ApiResponse<>(null, "Keyword must be provided.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        Page<GetAllBookResponse> searchedBooks = bookRepository.findByTitleOrAuthor(keyword, pageable);
        ApiResponse<Object> response = new ApiResponse<>(null, "Keyword must be provided.");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    // Get Overdue Books
    @Override
    public Page<GetOverdueBooks> getOverdueBooks(Pageable pageable) {
        return bookRepository.findOverdueBooks(pageable);
    }

    // Check Available Copies of Book
    @Override
    public ResponseEntity<ApiResponse<Object>> getAvailableBookCopiesById(Long bookId) {
        Optional<BookEntity> book = bookRepository.findById(bookId);
        if (book != null) {
            ApiResponse<Object> response = new ApiResponse<>(book, "");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponse<Object> response = new ApiResponse<>(null, "Book not found.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    // Delete Book By Id
    @Override
    public ResponseEntity<ApiResponse<Object>> deleteBook(Long bookId) {
        Optional<BookEntity> book = bookRepository.findById(bookId);
        if (book != null) {
            ApiResponse<Object> response = new ApiResponse<>(null, "Book deleted successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponse<Object> response = new ApiResponse<>(null, "Cannot delete book with active loans.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

}
