package com.project.library.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class BookRentalRequest {
    @NotNull
    private Long studentId;

    private List<Long> bookIds;
}
