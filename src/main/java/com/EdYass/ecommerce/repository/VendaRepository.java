package com.EdYass.ecommerce.repository;

import com.EdYass.ecommerce.entity.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface VendaRepository extends JpaRepository<Venda, Long> {
    List<Venda> findByDataBetween(LocalDateTime startDate, LocalDateTime endDate);
    boolean existsByProdutosId(Long produtoId);
}
