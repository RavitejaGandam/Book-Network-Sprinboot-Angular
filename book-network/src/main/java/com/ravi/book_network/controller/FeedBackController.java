package com.ravi.book_network.controller;

import com.ravi.book_network.common.PageResponse;
import com.ravi.book_network.feedback.FeedbackRequest;
import com.ravi.book_network.feedback.FeedbackResponse;
import com.ravi.book_network.service.FeedbackService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.naming.OperationNotSupportedException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feedbacks")
@Tag(name = "Feedback")
public class FeedBackController {

    private final FeedbackService service;

    @PostMapping("/giveFeedback")
    public ResponseEntity<Integer> saveFeedBack(
            @Valid @RequestBody FeedbackRequest request,
            Authentication connectedUser
    ) throws OperationNotSupportedException {
        return ResponseEntity.ok(service.save(request,connectedUser));
    }

    @GetMapping("/feedbackBook/{book-id}")
    public ResponseEntity<PageResponse<FeedbackResponse>> findAllFeedbackByBook(
            @PathVariable("book-id") Integer bookId,
            @RequestParam(required = false,defaultValue = "0",name = "page") int page,
            @RequestParam(required = false,defaultValue = "10",name = "size") int size,
            Authentication connectedUser
    ){
        return ResponseEntity.ok(service.findAllFeedbackByBook(bookId,page,size,connectedUser));
    }
}
