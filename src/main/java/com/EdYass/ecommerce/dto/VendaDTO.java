package com.EdYass.ecommerce.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public class VendaDTO {
    @NotNull
    private LocalDateTime data;

    @NotNull
    private List<Long> produtoId;
}
