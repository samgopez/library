package com.project.library.dto;

import com.project.library.entity.Book;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookRentalDto {
    private Long id;
    private String name;
    private boolean success;
    private String message;

    public static BookRentalDto success(Book book) {
        return BookRentalDto.builder()
                .id(book.getId())
                .name(book.getName())
                .success(true)
                .message("Successfully rented book")
                .build();
    }

    public static BookRentalDto notFound(Long bookId) {
        return BookRentalDto.builder()
                .id(bookId)
                .name("")
                .success(false)
                .message("Book not found")
                .build();
    }

    public static BookRentalDto notAvailable(Book book) {
        return BookRentalDto.builder()
                .id(book.getId())
                .name(book.getName())
                .success(false)
                .message("Book is not available")
                .build();
    }
}
