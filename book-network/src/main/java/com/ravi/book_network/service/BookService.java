package com.ravi.book_network.service;

import com.ravi.book_network.book.Book;
import com.ravi.book_network.book.BookRequest;
import com.ravi.book_network.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookMapper bookMapper;
    public Integer save(BookRequest request, Authentication connectedUser) {
        User user = ((User) connectedUser.getPrincipal());
        Book book = bookMapper.toBook(request);
        return request.id();
    }
}
