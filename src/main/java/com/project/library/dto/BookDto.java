package com.project.library.dto;

import com.project.library.entity.Book;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class BookDto {
    private Long id;
    private String name;
    @Builder.Default
    private List<AuthorDto> authors = new ArrayList<>();

    public static BookDto from(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .name(book.getName())
                .authors(book.getAuthors()
                        .stream()
                        .map(AuthorDto::from)
                        .collect(Collectors.toList()))
                .build();
    }
}
