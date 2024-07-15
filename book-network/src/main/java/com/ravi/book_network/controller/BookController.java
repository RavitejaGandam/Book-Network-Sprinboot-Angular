package com.ravi.book_network.controller;

import com.ravi.book_network.book.BookRequest;
import com.ravi.book_network.book.BookResponse;
import com.ravi.book_network.common.PageResponse;
import com.ravi.book_network.service.BookService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Book")
@RequestMapping("books")
public class BookController {

    private final BookService service;

    @PostMapping("/saveBook")
    public ResponseEntity<Integer> saveBook(@Valid @RequestBody BookRequest request,
                                            Authentication connectedUser){
        return ResponseEntity.ok(service.save(request,connectedUser));
    }

    @GetMapping("/{book-id}")
    public ResponseEntity<BookResponse> findBookById(@PathVariable("book-id") Integer bookId){
        return ResponseEntity.ok(service.findBookById(bookId));
    }

    @GetMapping("/getAllBooks")
    public ResponseEntity<PageResponse<BookResponse>> findAllBooks(
            @RequestParam(name = "page" , defaultValue = "0",required = false ) int page,
            @RequestParam(name = "size" , defaultValue = "10",required = false ) int size,
           Authentication connectedUser){
        return ResponseEntity.ok(service.findAllBooks(page,size,connectedUser));
    }
}
