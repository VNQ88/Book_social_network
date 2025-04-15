package com.vnq.booknetwork.book;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BorrowedBookResponse {
    private Integer id;
    private String title;
    private String author;
    private String isbn;
    private double rating;
    private boolean returned;
    private boolean returnApproved;

}
