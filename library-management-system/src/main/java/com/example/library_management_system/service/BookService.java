package com.example.library_management_system.service;

import com.example.library_management_system.dto.request.book.CreateBookRequest;
import com.example.library_management_system.dto.request.book.UpdateBookRequest;
import com.example.library_management_system.dto.response.book.GetAllBookResponse;
import com.example.library_management_system.dto.response.book.GetOverdueBooks;
import com.example.library_management_system.util.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface BookService {

    // Add a New Book
    ResponseEntity<ApiResponse<Object>> saveBook(CreateBookRequest bookDetails);

    //Update Book Details
    ResponseEntity<ApiResponse<Object>> updateBook(Long bookId, UpdateBookRequest bookDetails);

    //Get All Available Books
    Page<GetAllBookResponse> getAllAvailableBooks(Pageable pageable);

    //Search Books by Title or Author
    ResponseEntity<ApiResponse<Object>> getBooksByKeyword(String keyword, Pageable pageable);

    //Get Overdue Books
    Page<GetOverdueBooks> getOverdueBooks(Pageable pageable);

    //Check Available Copies of Book
    ResponseEntity<ApiResponse<Object>> getAvailableBookCopiesById(Long bookId);

    //Delete Book By Id
    ResponseEntity<ApiResponse<Object>> deleteBook (Long bookId);

}
