package com.vnq.booknetwork.book;

import com.vnq.booknetwork.common.BaseEntity;
import com.vnq.booknetwork.feedback.Feedback;
import com.vnq.booknetwork.history.BookTransactionHistory;
import com.vnq.booknetwork.user.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.Transient;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "books")
public class Book extends BaseEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "synopsis")
    private String synopsis;

    @Column(name = "book_cover_url")
    private String bookCoverUrl;

    @Column(name = "archived")
    private boolean archived;

    @Column(name = "shareable")
    private boolean shareable;

    @CreatedBy
    @Column(name = "created_by", nullable = false, updatable = false)
    private Integer createdBy;

    @LastModifiedBy
    @Column(name = "last_modified_by", insertable = false)
    private Integer lastModifiedBy;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @OneToMany
    @JoinColumn(name = "book")
    @Column(name = "feedbacks")
    private List<Feedback> feedbacks;

    @OneToMany(mappedBy = "book")
    private List<BookTransactionHistory> bookTransactionHistories;

    @Transient
    public double getRating() {
        if (feedbacks == null || feedbacks.isEmpty()) {
            return 0.0;
        }
        var rate = this.feedbacks.stream()
                .mapToDouble(Feedback::getNote)
                .average()
                .orElse(0.0);
        return Math.round(rate * 10.0) / 10.0;
    }
}
