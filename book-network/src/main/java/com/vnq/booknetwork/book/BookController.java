package com.vnq.booknetwork.book;

import com.vnq.booknetwork.common.PageRespone;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RequestMapping("books")
@RestController
@Tag(name = "Book", description = "Book API")
public class BookController {
    private final BookService bookService;

    @PostMapping
    public ResponseEntity<Integer> saveBook(
            @Valid @RequestBody BookRequest request,
            Authentication authentication) {
        return ResponseEntity.ok(bookService.saveBook(request, authentication));
    }

    @GetMapping("{book-id}")
    public ResponseEntity<BookResponse> getBook(
            @PathVariable("book-id") Integer bookId,
            Authentication authentication) {
        return ResponseEntity.ok(bookService.findById(bookId));
    }

    @GetMapping
    public ResponseEntity<PageRespone<BookResponse>> getAllBooks(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size,
            Authentication authentication) {
        return ResponseEntity.ok(bookService.getAllBooks(page, size, authentication));
    }

    @GetMapping("/owner")
    public ResponseEntity<PageRespone<BookResponse>> getAllBooksByOwner(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size,
            Authentication authentication) {
        return ResponseEntity.ok(bookService.getAllBooksByOwner(page, size, authentication));
    }

    @GetMapping("/borrowed")
    public ResponseEntity<PageRespone<BorrowedBookResponse>> getAllBooksBorrowed(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size,
            Authentication authentication) {
        return ResponseEntity.ok(bookService.getAllBooksBorrowed(page, size, authentication));
    }

    @GetMapping("/returned")
    public ResponseEntity<PageRespone<BorrowedBookResponse>> getAllBooksReturned(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size,
            Authentication authentication) {
        return ResponseEntity.ok(bookService.getAllBooksReturned(page, size, authentication));
    }

    @PatchMapping("/shareable/{book-id}")
    public ResponseEntity<Integer> updateBookShareableStatus(
            @PathVariable("book-id") Integer bookId,
            Authentication authentication) {
        return ResponseEntity.ok(bookService.updateBookShareableStatus(bookId,  authentication));
    }

    @PatchMapping("/archived/{book-id}")
    public ResponseEntity<Integer> updateBookArchivedStatus(
            @PathVariable("book-id") Integer bookId,
            Authentication authentication) {
        return ResponseEntity.ok(bookService.updateBookArchivedStatus(bookId,  authentication));
    }

    @PostMapping("/borrow/{book-id}")
    public ResponseEntity<Integer> borrowBook(
            @PathVariable("book-id") Integer bookId,
            Authentication authentication
    ){
        return ResponseEntity.ok(bookService.borrowBook(bookId, authentication));
    }

    @PatchMapping("/borrow/return/{book-id}")
    public ResponseEntity<Integer> returnBorrowedBook(
            @PathVariable("book-id") Integer bookId,
            Authentication authentication
    ){
        return ResponseEntity.ok(bookService.returnBorrowedBook(bookId, authentication));
    }

    @PatchMapping("/borrow/return/approve/{book-id}")
    public ResponseEntity<Integer> approveReturnedBook(
            @PathVariable("book-id") Integer bookId,
            Authentication authentication
    ){
        return ResponseEntity.ok(bookService.approveReturnedBook(bookId, authentication));
    }

    @PostMapping(value = "/cover/{book-id}", consumes = {"multipart/form-data"})
    public ResponseEntity<?> uploadBookCoverPicture(
            @PathVariable("book-id") Integer bookId,
            @Parameter()
            @RequestPart("file") MultipartFile file,
            Authentication authentication
    ){
        bookService.uploadBookCoverPicture(bookId, file, authentication);
        return ResponseEntity.accepted().build();
    }

}
