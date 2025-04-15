package com.vnq.booknetwork.feedback;

import com.vnq.booknetwork.common.PageRespone;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("feedbacks")
@RequiredArgsConstructor
@Tag(name = "Feedback", description = "Feedback API")
public class FeedbackController {
    private final FeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<Integer> saveFeedback(FeedbackRequest request,
                                                  Authentication authentication) {
        return ResponseEntity.ok(feedbackService.saveFeedback(request, authentication));
    }

    @GetMapping("/book/{book-id}")
    public ResponseEntity<PageRespone<FeedbackResponse>> getAllFeedbacksByBook(
            @PathVariable("book-id") Integer bookId,
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size,
            Authentication authentication) {
        return ResponseEntity.ok(feedbackService.getAllFeedbacksByBook(bookId, page, size, authentication));
    }
}
