package com.project.library.repository;

import com.project.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(
            "SELECT book FROM Book book " +
            "   JOIN book.authors authors " +
            "   WHERE book.totalCopies > size(book.bookRentals) " +
            "   AND authors.id = :authorId "
    )
    List<Book> findAllAvailableByAuthorId(Long authorId);
}
