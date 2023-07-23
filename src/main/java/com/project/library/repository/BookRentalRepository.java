package com.project.library.repository;

import com.project.library.entity.BookRental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRentalRepository extends JpaRepository<BookRental, Long> {
}
