package com.vnq.booknetwork.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>, JpaSpecificationExecutor<Book> {
    @Query("SELECT b FROM Book b WHERE b.archived = false AND b.shareable = true AND b.owner.id != :userId")
    Page<Book> findAllDisplayableBooks(Pageable pageable, Integer userId);

    Page<Book> findAll(Specification<Book> bookSpecification, Pageable pageable);
}
