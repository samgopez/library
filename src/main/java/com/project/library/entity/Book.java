package com.project.library.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "book")
public class Book extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "book_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors = new ArrayList<>();

    @OneToMany(mappedBy = "book")
    private List<BookRental> bookRentals = new ArrayList<>();

    @Column(name = "total_copies", nullable = false)
    private Integer totalCopies;

    @Transient
    public boolean isAvailableForRent() {
        return this.totalCopies > this.bookRentals.size();
    }
}
