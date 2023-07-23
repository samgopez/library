package com.project.library.service;

import com.project.library.controller.request.BookRentalRequest;
import com.project.library.dto.BookDto;
import com.project.library.dto.BookRentalDto;

import java.util.List;

public interface BookService {

    List<BookDto> getAvailableBooksByAuthorId(Long authorId);

    List<BookRentalDto> processBookRentals(BookRentalRequest bookRentalRequest);
}
