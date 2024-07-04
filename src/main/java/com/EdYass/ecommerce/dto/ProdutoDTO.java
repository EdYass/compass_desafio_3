package com.EdYass.ecommerce.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class ProdutoDTO {
    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    @Positive
    private BigDecimal preco;

    @Min(0)
    private int stock;

    public @NotBlank String getNome() {
        return nome;
    }

    public void setNome(@NotBlank String nome) {
        this.nome = nome;
    }

    public @NotBlank String getDescricao() {
        return descricao;
    }

    public void setDescricao(@NotBlank String descricao) {
        this.descricao = descricao;
    }

    public @Positive BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(@Positive BigDecimal preco) {
        this.preco = preco;
    }

    @Min(0)
    public int getStock() {
        return stock;
    }

    public void setStock(@Min(0) int stock) {
        this.stock = stock;
    }
}
