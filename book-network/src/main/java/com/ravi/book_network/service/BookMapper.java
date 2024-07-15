package com.ravi.book_network.service;

import com.ravi.book_network.book.Book;
import com.ravi.book_network.book.BookRequest;
import org.springframework.stereotype.Service;

@Service
public class BookMapper {

    public Book toBook(BookRequest request) {
        return Book.builder()
                        .id(request.id())
                        .title(request.title())
                        .authorName(request.authorName())
                        .synopsis(request.synopsis())
                        .archived(false)
                        .shareable(request.shareable())
                        .build();
        //start from 4:13:00
    }
}
