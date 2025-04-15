package com.vnq.booknetwork.history;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookTransactionHistoryRepository extends JpaRepository<BookTransactionHistory, Integer> {

    @Query("SELECT history FROM BookTransactionHistory history WHERE history.user.id = :userId")
    Page<BookTransactionHistory> findAllByBorrowedBook(Pageable pageable, Integer userId);

    @Query("SELECT history FROM BookTransactionHistory history WHERE  history.book.owner.id = :userId")
    Page<BookTransactionHistory> findAllReturnedBook(Pageable pageable, Integer userId);

    @Query("SELECT (COUNT(*) > 0) FROM BookTransactionHistory history WHERE history.book.id = :bookId AND history.user.id = :userId AND history.returnApproved = false" )
    boolean isAlreadyBorrowedByUser(Integer bookId, Integer userId);

    @Query("SELECT transaction FROM BookTransactionHistory transaction WHERE transaction.book.id = :bookId AND transaction.user.id = :userId AND transaction.returned = false AND transaction.returnApproved = false")
    Optional<BookTransactionHistory> findByBookIdAndUserId(Integer bookId, Integer userId);

    @Query("SELECT transaction FROM BookTransactionHistory transaction WHERE transaction.book.id = :bookId AND transaction.book.owner.id = :ownerId AND transaction.returned = true AND transaction.returnApproved = false")
    Optional<BookTransactionHistory> findByBookIdAndOwnerId(Integer bookId, Integer ownerId);
}
