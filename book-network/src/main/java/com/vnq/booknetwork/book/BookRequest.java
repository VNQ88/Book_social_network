package com.vnq.booknetwork.book;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record BookRequest(Integer id,
//                          @NotNull(message = "100")
//                          @NotEmpty(message = "100")
                          @NotNull(message = "Title is required")
                                  @NotEmpty(message = "Title is required")
                          String title,
                            @NotNull(message = "Author is required")
                                    @NotEmpty(message = "Author is required")
                          String author,
                            @NotNull(message = "ISBN is required")
                                    @NotEmpty(message = "ISBN is required")
                          String isbn,
                          @NotNull(message = "ISBN is required")
                          @NotEmpty(message = "ISBN is required")
                          String synopsis,

                          boolean shareable) {
}
