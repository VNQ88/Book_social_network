package com.vnq.booknetwork.feedback;

import com.vnq.booknetwork.book.Book;
import com.vnq.booknetwork.book.BookRepository;
import com.vnq.booknetwork.common.PageRespone;
import com.vnq.booknetwork.exception.OperationNotPermittedException;
import com.vnq.booknetwork.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackService {
    private final BookRepository bookRepository;
    private final FeedbackMapper feedBackMapper;
    private final FeedbackRepository feedbackRepository;
    public Integer saveFeedback(FeedbackRequest request, Authentication authentication) {
        Book book = bookRepository.findById(request.bookId())
                .orElseThrow(() -> new OperationNotPermittedException("Book not found with id: " + request.bookId()));
        if (!book.isShareable() || book.isArchived()){
            throw new OperationNotPermittedException("Book is archived or not shareable");
        }
        User user = (User) authentication.getPrincipal();
        if (user.getId().equals(book.getOwner().getId())) {
            throw new OperationNotPermittedException("You cannot give feedback to your own book");
        }
        Feedback feedback = feedBackMapper.toFeedback(request);
        return feedbackRepository.save(feedback).getId();
    }

    public PageRespone<FeedbackResponse> getAllFeedbacksByBook(Integer bookId, int page, int size, Authentication authentication) {
        Pageable pageable = PageRequest.of(page, size);
        User user = (User) authentication.getPrincipal();
        Page<Feedback> feedbacks = feedbackRepository.findAllByBookId(bookId, pageable);
        List<FeedbackResponse> feedbackResponses = feedbacks.stream()
                .map(feedback -> feedBackMapper.toFeedbackResponse(feedback, user.getId()))
                .toList();

        return PageRespone.<FeedbackResponse>builder()
                .content(feedbackResponses)
                .number(feedbacks.getNumber())
                .size(feedbacks.getSize())
                .totalElements(feedbacks.getTotalElements())
                .totalPages(feedbacks.getTotalPages())
                .first(feedbacks.isFirst())
                .last(feedbacks.isLast())
                .build();
    }
}
