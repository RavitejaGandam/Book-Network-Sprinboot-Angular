package com.ravi.book_network.service;

import com.ravi.book_network.book.Book;
import com.ravi.book_network.entity.User;
import com.ravi.book_network.feedback.Feedback;
import com.ravi.book_network.feedback.FeedbackMapper;
import com.ravi.book_network.feedback.FeedbackRequest;
import com.ravi.book_network.repositories.BookRepository;
import com.ravi.book_network.repositories.FeedbackRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.naming.OperationNotSupportedException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FeedbackService {
    private final BookRepository bookRepository;
    private final FeedbackMapper feedbackMapper;
    private final FeedbackRepository feedbackRepository;
    public Integer save(FeedbackRequest request, Authentication connectedUser) throws OperationNotSupportedException {
        Book book = bookRepository.findById(request.bookId())
                .orElseThrow(()->new EntityNotFoundException("Book associated with id : " + request.bookId()+" is not found"));
        if (book.isArchived() || !book.isShareable()){
            throw new OperationNotSupportedException("You cannot give feedback as this book  is not able to share or archived");
        }
        User user = ((User) connectedUser.getPrincipal());
        if(Objects.equals(book.getOwner().getId(),user.getId())){
            throw new OperationNotSupportedException("You cannot do this operation as you are the owner of the book ");

        }
        Feedback feedback = FeedbackMapper.toFeedback(request);
        return feedbackRepository.save(feedback).getId();

    }
}
