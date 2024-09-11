package com.example.library_management_system.service.impl;

import com.example.library_management_system.entity.BookEntity;
import com.example.library_management_system.repository.BookRepository;
import com.example.library_management_system.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    //Add a New Book
//    @Override
//    public ResponseEntity<ApiResponse<Object>> saveBook(CreateBookRequest book) {
//
//        BookEntity newBook = new BookEntity();
//        newBook.setTitle(book.getTitle());
//        newBook.setAuthor(book.getAuthor());
//        newBook.setIsbn(book.getIsbn());
//        newBook.setQuantity(book.getQuantity());
//
//        bookRepository.save(newBook);
//    }
//
}
