package com.vnq.booknetwork.history;

import com.vnq.booknetwork.book.Book;
import com.vnq.booknetwork.common.BaseEntity;
import com.vnq.booknetwork.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "book_transaction_history")
public class BookTransactionHistory extends BaseEntity {

    private boolean returned;
    private  boolean returnApproved;

    @CreatedBy
    @Column(name = "created_by", nullable = false, updatable = false)
    private Integer createdBy;

    @LastModifiedBy
    @Column(name = "last_modified_by", insertable = false)
    private Integer lastModifiedBy;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;


}
