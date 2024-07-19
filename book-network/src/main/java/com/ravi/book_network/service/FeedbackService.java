package com.ravi.book_network.service;

import com.ravi.book_network.book.Book;
import com.ravi.book_network.common.PageResponse;
import com.ravi.book_network.entity.User;
import com.ravi.book_network.feedback.Feedback;
import com.ravi.book_network.feedback.FeedbackMapper;
import com.ravi.book_network.feedback.FeedbackRequest;
import com.ravi.book_network.feedback.FeedbackResponse;
import com.ravi.book_network.repositories.BookRepository;
import com.ravi.book_network.repositories.FeedbackRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.naming.OperationNotSupportedException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedbackService {
    private final BookRepository bookRepository;
    private final FeedbackMapper feedbackMapper;
    private final FeedbackRepository feedbackRepository;
   // private final FeedbackResponse feedbackResponse;
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

    public PageResponse<FeedbackResponse> findAllFeedbackByBook(Integer bookId, int page, int size, Authentication connectedUser) {
        Pageable pageable = PageRequest.of(page,size);
        User user = ((User) connectedUser.getPrincipal());
        Page<Feedback> feedbacks = feedbackRepository.findAllFeedbackByBook(bookId,pageable);
        List<FeedbackResponse> feedbackResponses = feedbacks.stream()
                .map(f ->feedbackMapper.toFeedbackResponse(f,user.getId()))
                .collect(Collectors.toList());
        return new PageResponse<>(
                feedbackResponses,
                feedbacks.getNumber(),
                feedbacks.getSize(),
                feedbacks.getTotalElements(),
                feedbacks.getTotalPages(),
                feedbacks.isFirst(),
                feedbacks.isLast()
        );
    }
}
