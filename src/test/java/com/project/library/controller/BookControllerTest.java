package com.project.library.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void givenAuthorIdShouldReturnAvailableBooks() throws Exception {
        this.mockMvc.perform(get("/api/v1/authors/{authorId}/books/available", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.[0].id").value(5))
                .andExpect(jsonPath("$.[0].name").value("Biology"))
                .andExpect(jsonPath("$.[0].authors", hasSize(2)))
                .andExpect(jsonPath("$.[0].authors.[0].id").value(5))
                .andExpect(jsonPath("$.[0].authors.[0].firstName").value("Caleb"))
                .andExpect(jsonPath("$.[0].authors.[0].lastName").value("Mitchell"))
                .andExpect(jsonPath("$.[0].authors.[1].id").value(1))
                .andExpect(jsonPath("$.[0].authors.[1].firstName").value("Jackson"))
                .andExpect(jsonPath("$.[0].authors.[1].lastName").value("Reed"))
        ;
    }

    @Test
    void givenStudentIdAndBookIdsWhenBookRentalRequestedShouldReturnBooksWithAppropriateRentalMessage() throws Exception {
        final String bookRentalRequest = """
                {
                    "studentId": 4,
                    "bookIds": [1, 8, 9]
                }
                """;

        this.mockMvc.perform(post("/api/v1/books/rent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookRentalRequest))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$.[0].id").value(1))
                .andExpect(jsonPath("$.[0].name").value("Mathematics"))
                .andExpect(jsonPath("$.[0].success").value(false))
                .andExpect(jsonPath("$.[0].message").value("Book is not available"))
                .andExpect(jsonPath("$.[1].id").value(8))
                .andExpect(jsonPath("$.[1].name").value("Music"))
                .andExpect(jsonPath("$.[1].success").value(true))
                .andExpect(jsonPath("$.[1].message").value("Successfully rented book"))
                .andExpect(jsonPath("$.[2].id").value(9))
                .andExpect(jsonPath("$.[2].name").value(""))
                .andExpect(jsonPath("$.[2].success").value(false))
                .andExpect(jsonPath("$.[2].message").value("Book not found"))
        ;
    }
}
