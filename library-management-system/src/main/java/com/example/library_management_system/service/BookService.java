package com.example.library_management_system.service;

import com.example.library_management_system.dto.request.book.CreateBookRequest;
import com.example.library_management_system.dto.request.book.UpdateBookRequest;
import com.example.library_management_system.dto.response.book.GetAllBookResponse;
import com.example.library_management_system.dto.response.book.GetOverdueBooks;
import com.example.library_management_system.util.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BookService {

    // Add a New Book
    ResponseEntity<ApiResponse<Object>> saveBook(CreateBookRequest bookDetails);

    //Update Book Details
    ResponseEntity<ApiResponse<Object>> updateBook(Long bookId, UpdateBookRequest bookDetails);

    //Get All Available Books
    List<GetAllBookResponse> getAllAvailableBooks();

    //Search Books by Title or Author
    ResponseEntity<ApiResponse<Object>> getBooksByKeyword(String keyword);

    //Get Overdue Books
    List<GetOverdueBooks> getOverdueBooks();

    //Check Available Copies of Book
    ResponseEntity<ApiResponse<Object>> getAvailableBookCopiesById(Long bookId);

    //Delete Book By Id
    ResponseEntity<ApiResponse<Object>> deleteBook (Long bookId);

}
