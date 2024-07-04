package com.EdYass.ecommerce.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public class VendaDTO {
    @NotNull
    private LocalDateTime data;

    @NotNull
    private List<Long> produtoIds;

    public @NotNull LocalDateTime getData() {
        return data;
    }

    public void setData(@NotNull LocalDateTime data) {
        this.data = data;
    }

    public @NotNull List<Long> getProdutoIds() {
        return produtoIds;
    }

    public void setProdutoIds(@NotNull List<Long> produtoId) {
        this.produtoIds = produtoId;
    }
}
