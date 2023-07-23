package com.project.library.controller;

import com.project.library.controller.request.BookRentalRequest;
import com.project.library.dto.BookDto;
import com.project.library.dto.BookRentalDto;
import com.project.library.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("api/v1/authors/{authorId}/books/available")
    public ResponseEntity<List<BookDto>> getAvailableBooksByAuthorId(@PathVariable Long authorId) {
        final List<BookDto> availableBooks = bookService.getAvailableBooksByAuthorId(authorId);
        return ResponseEntity.ok(availableBooks);
    }

    @PostMapping("api/v1/books/rent")
    public ResponseEntity<List<BookRentalDto>> rentBooks(@Valid @RequestBody BookRentalRequest bookRentalRequest) {
        final List<BookRentalDto> bookRentals = bookService.processBookRentals(bookRentalRequest);
        return ResponseEntity.ok(bookRentals);
    }
}
