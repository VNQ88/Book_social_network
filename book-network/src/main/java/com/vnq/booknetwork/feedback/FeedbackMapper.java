package com.vnq.booknetwork.feedback;

import com.vnq.booknetwork.book.Book;
import com.vnq.booknetwork.user.User;
import org.springframework.stereotype.Service;

@Service
public class FeedbackMapper {
    public Feedback toFeedback(FeedbackRequest request) {
        return Feedback.builder()
                .note(request.note())
                .comment(request.comment())
                .book(Book.builder().id(request.bookId()).build())
                .build();
    }

    public FeedbackResponse toFeedbackResponse(Feedback feedback, Integer userId) {
        return FeedbackResponse.builder()
                .note(feedback.getNote())
                .comment(feedback.getComment())
                .ownFeedback(feedback.getCreatedBy().equals(userId))
                .build();
    }
}
