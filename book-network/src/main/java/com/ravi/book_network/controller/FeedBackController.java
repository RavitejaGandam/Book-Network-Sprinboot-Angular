package com.ravi.book_network.controller;

import com.ravi.book_network.feedback.FeedbackRequest;
import com.ravi.book_network.service.FeedbackService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
