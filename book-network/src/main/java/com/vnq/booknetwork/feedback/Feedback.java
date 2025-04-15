package com.vnq.booknetwork.feedback;

import com.vnq.booknetwork.book.Book;
import com.vnq.booknetwork.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
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
@Table(name = "feedbacks")
public class Feedback extends BaseEntity {

    private Double note;
    private String comment;

    @CreatedBy
    @Column(name = "created_by", nullable = false, updatable = false)
    private Integer createdBy;

    @LastModifiedBy
    @Column(name = "last_modified_by", insertable = false)
    private Integer lastModifiedBy;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;
}
