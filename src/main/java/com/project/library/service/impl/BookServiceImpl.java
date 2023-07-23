package com.project.library.service.impl;

import com.project.library.controller.request.BookRentalRequest;
import com.project.library.dto.BookDto;
import com.project.library.dto.BookRentalDto;
import com.project.library.entity.BookRental;
import com.project.library.entity.Student;
import com.project.library.exception.NotFoundException;
import com.project.library.repository.AuthorRepository;
import com.project.library.repository.BookRentalRepository;
import com.project.library.repository.BookRepository;
import com.project.library.repository.StudentRepository;
import com.project.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookRentalRepository bookRentalRepository;
    private final StudentRepository studentRepository;
    private final AuthorRepository authorRepository;

    @Override
    public List<BookDto> getAvailableBooksByAuthorId(Long authorId) {
        authorRepository.findById(authorId).orElseThrow(() -> new NotFoundException("Author not found"));

        return bookRepository.findAllAvailableByAuthorId(authorId)
                .stream()
                .map(BookDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookRentalDto> processBookRentals(BookRentalRequest bookRentalRequest) {
        final Student existingStudent = studentRepository.findById(bookRentalRequest.getStudentId())
                .orElseThrow(() -> new NotFoundException("Student not found"));

        return bookRentalRequest.getBookIds().stream()
                .map(bookId -> findAndRentBookForStudent(bookId, existingStudent))
                .collect(Collectors.toList());
    }

    private BookRentalDto findAndRentBookForStudent(Long bookId, Student student) {
        return bookRepository.findById(bookId)
                .map(existingBook -> {
                    if (existingBook.isAvailableForRent()) {
                        BookRental bookRental = BookRental.builder()
                                .book(existingBook)
                                .student(student)
                                .build();
                        bookRentalRepository.save(bookRental);
                        return BookRentalDto.success(existingBook);
                    }

                    return BookRentalDto.notAvailable(existingBook);
                })
                .orElse(BookRentalDto.notFound(bookId));
    }
}
