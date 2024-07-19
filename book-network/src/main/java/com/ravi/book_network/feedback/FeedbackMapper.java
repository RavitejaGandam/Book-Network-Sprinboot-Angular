package com.ravi.book_network.feedback;

import com.ravi.book_network.book.Book;
import org.springframework.stereotype.Service;

@Service
public class FeedbackMapper {

    public static Feedback toFeedback(FeedbackRequest request) {
        return Feedback.builder()
                .note(request.note())
                .comment(request.comment())
                .book(Book.builder()
                        .id(request.bookId())
                        .archived(false)
                        .shareable(false)
                        .build())
                .build();
    }
}
