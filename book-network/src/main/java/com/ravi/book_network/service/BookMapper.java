package com.ravi.book_network.service;

import com.ravi.book_network.book.Book;
import com.ravi.book_network.book.BookRequest;
import com.ravi.book_network.book.BookResponse;
import com.ravi.book_network.book.BorrowedBookResponse;
import com.ravi.book_network.file.FileUtils;
import com.ravi.book_network.history.BookTransactionHistory;
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

    public BookResponse toBookResponse(Book book) {
        return BookResponse.builder()
                        .id(book.getId())
                        .title(book.getTitle())
                        .authorName(book.getAuthorName())
                        .isbn(book.getIsbn())
                        .synopsis(book.getSynopsis())
                        .rate(book.getRate())
                        .archived(book.isArchived())
                        .shareable(book.isShareable())
                        .owner(book.getOwner().fullName())
                       .cover(FileUtils.readFromLocation(book.getBookCover()))
                        .build();
    }

    public BorrowedBookResponse toBorrowedBookResponse(BookTransactionHistory history) {
        return BorrowedBookResponse.builder()
                .id(history.getId())
                .title(history.getBook().getTitle())
                .authorName(history.getBook().getAuthorName())
                .isbn(history.getBook().getIsbn())
                .rate(history.getBook().getRate())
                .returned(history.getReturned())
                .returnApproved(history.getReturnApproved())
                .build();
    }
}
