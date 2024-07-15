package com.ravi.book_network.controller;

import com.ravi.book_network.book.BookRequest;
import com.ravi.book_network.service.BookService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
