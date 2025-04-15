package com.vnq.booknetwork.book;

import com.vnq.booknetwork.common.PageRespone;
import com.vnq.booknetwork.exception.OperationNotPermittedException;
import com.vnq.booknetwork.file.FileStorageService;
import com.vnq.booknetwork.history.BookTransactionHistory;
import com.vnq.booknetwork.history.BookTransactionHistoryRepository;
import com.vnq.booknetwork.user.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final FileStorageService fileStorageService;
    private final BookTransactionHistoryRepository transactionHistoryRepository;
    private static final String PAGE_SORT_BY = "createdAt";

    public Integer saveBook(BookRequest request, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Book book = bookMapper.toBook(request);
        book.setOwner(user);
        return bookRepository.save(book).getId();
    }

    public BookResponse findById(Integer bookId) {
        return bookRepository.findById(bookId)
                .map(bookMapper::toBookResponse)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));
    }

    public PageRespone<BookResponse> getAllBooks(int page, int size, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Pageable pageable = PageRequest.of(page, size, Sort.by(PAGE_SORT_BY).descending());
        Page<Book> books = bookRepository.findAllDisplayableBooks(pageable, user.getId());
        return getBookResponsePageRespone(books);
    }

    public PageRespone<BookResponse> getAllBooksByOwner(int page, int size, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Pageable pageable = PageRequest.of(page, size, Sort.by(PAGE_SORT_BY).descending());
        Page<Book> books = bookRepository.findAll(BookSpecification.withOwnerId(user.getId()), pageable);
        return getBookResponsePageRespone(books);
    }

    private PageRespone<BookResponse> getBookResponsePageRespone(Page<Book> books) {
        List<BookResponse> bookResponses = books.getContent().stream()
                .map(bookMapper::toBookResponse)
                .toList();
        return PageRespone.<BookResponse>builder()
                .content(bookResponses)
                .number(books.getNumber())
                .size(books.getSize())
                .totalElements(books.getTotalElements())
                .totalPages(books.getTotalPages())
                .first(books.isFirst())
                .last(books.isLast())
                .build();
    }

    public PageRespone<BorrowedBookResponse> getAllBooksBorrowed(int page, int size, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Pageable pageable = PageRequest.of(page, size, Sort.by(PAGE_SORT_BY).descending());
        Page<BookTransactionHistory> allBorrowedBooks = transactionHistoryRepository.findAllByBorrowedBook(pageable, user.getId());
        return getBorrowedBookResponsePageRespone(allBorrowedBooks);
    }

    public PageRespone<BorrowedBookResponse> getAllBooksReturned(int page, int size, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Pageable pageable = PageRequest.of(page, size, Sort.by(PAGE_SORT_BY).descending());
        Page<BookTransactionHistory> allReturnedBooks = transactionHistoryRepository.findAllReturnedBook(pageable, user.getId());
        return getBorrowedBookResponsePageRespone(allReturnedBooks);
    }

    private PageRespone<BorrowedBookResponse> getBorrowedBookResponsePageRespone(Page<BookTransactionHistory> allReturnedBooks) {
        List<BorrowedBookResponse> bookResponses = allReturnedBooks.getContent().stream()
                .map(bookMapper::toBorrowedBookResponse)
                .toList();
        return PageRespone.<BorrowedBookResponse>builder()
                .content(bookResponses)
                .number(allReturnedBooks.getNumber())
                .size(allReturnedBooks.getSize())
                .totalElements(allReturnedBooks.getTotalElements())
                .totalPages(allReturnedBooks.getTotalPages())
                .first(allReturnedBooks.isFirst())
                .last(allReturnedBooks.isLast())
                .build();
    }

    public Integer updateBookShareableStatus(Integer bookId, Authentication authentication) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book with id: " + bookId + "not found"));
        User user = (User) authentication.getPrincipal();
        if (book.getOwner().getId().equals(user.getId())) {
            book.setShareable(!book.isShareable());
            return bookRepository.save(book).getId();
        } else {
            throw new OperationNotPermittedException("You are not the owner of this book");
        }
    }

    public Integer updateBookArchivedStatus(Integer bookId, Authentication authentication) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book with id: " + bookId + "not found"));
        User user = (User) authentication.getPrincipal();
        if (book.getOwner().getId().equals(user.getId())) {
            book.setArchived(!book.isArchived());
            return bookRepository.save(book).getId();
        } else {
            throw new OperationNotPermittedException("You are not the owner of this book");
        }
    }

    public Integer borrowBook(Integer bookId, Authentication authentication) {
        Book book = bookRepository.findById(bookId).orElseThrow(
                () -> new EntityNotFoundException("Book with id: " + bookId + "not found")
        );
        if (book.isArchived() || !book.isShareable()){
            throw new OperationNotPermittedException("The requested book cannot be borrowed because it is archived or not sharable");
        }

        User user = (User) authentication.getPrincipal();
        if (Objects.equals(book.getOwner().getId(), user.getId())){
            throw new OperationNotPermittedException("You cannot borrow your own book");
        }
        final boolean isAlreadyBorrowed = transactionHistoryRepository.isAlreadyBorrowedByUser(bookId, user.getId());
        if (isAlreadyBorrowed) {
            throw new OperationNotPermittedException("You have already borrowed this book");
        }
        BookTransactionHistory transactionHistory = BookTransactionHistory.builder()
                .book(book)
                .user(user)
                .returned(false)
                .returnApproved(false)
                .build();
        return transactionHistoryRepository.save(transactionHistory).getId() ;
    }

    public Integer returnBorrowedBook(Integer bookId, Authentication authentication) {
        Book book = bookRepository.findById(bookId).orElseThrow(
                () -> new EntityNotFoundException("Book with id: " + bookId + "not found")
        );
        if (book.isArchived() || !book.isShareable()){
            throw new OperationNotPermittedException("The requested book cannot be borrowed because it is archived or not sharable");
        }
        User user = (User) authentication.getPrincipal();
        if (Objects.equals(book.getOwner().getId(), user.getId())){
            throw new OperationNotPermittedException("You cannot return your own book");
        }
        BookTransactionHistory transactionHistory = transactionHistoryRepository.findByBookIdAndUserId(bookId, user.getId())
                .orElseThrow(() -> new OperationNotPermittedException("You have not borrowed this book"));
        transactionHistory.setReturned(true);
        return transactionHistoryRepository.save(transactionHistory).getId();
    }

    public Integer approveReturnedBook(Integer bookId, Authentication authentication) {
        Book book = bookRepository.findById(bookId).orElseThrow(
                () -> new EntityNotFoundException("Book with id: " + bookId + "not found")
        );
        if (book.isArchived() || !book.isShareable()){
            throw new OperationNotPermittedException("The requested book cannot be borrowed because it is archived or not sharable");
        }
        User user = (User) authentication.getPrincipal();
        if (!Objects.equals(book.getOwner().getId(), user.getId())){
            throw new OperationNotPermittedException("You are not the owner of this book");
        }
        BookTransactionHistory transactionHistory = transactionHistoryRepository.findByBookIdAndOwnerId(bookId, book.getOwner().getId())
                .orElseThrow(() -> new OperationNotPermittedException("The book is not returned yet. Please wait for the user to return the book"));
        transactionHistory.setReturnApproved(true);
        return transactionHistoryRepository.save(transactionHistory).getId();
    }

    public void uploadBookCoverPicture(Integer bookId, MultipartFile file, Authentication authentication) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book with id: " + bookId + "not found"));
        User user = (User) authentication.getPrincipal();
        var bookCover = fileStorageService.saveFile(file, book, user.getId());
        if (book.getOwner().getId().equals(user.getId())) {
            book.setBookCoverUrl(bookCover);
            bookRepository.save(book);
        } else {
            throw new OperationNotPermittedException("You are not the owner of this book");
        }
    }
}
