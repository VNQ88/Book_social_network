package com.vnq.booknetwork.book;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {
    private Integer id;
    private String title;
    private String author;
    private String isbn;
    private String synopsis;
    private boolean archived;
    private boolean shareable;
    private String owner;
    private byte[] bookCover;
    private double rating;

}
