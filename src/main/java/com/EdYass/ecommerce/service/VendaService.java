package com.EdYass.ecommerce.service;

import com.EdYass.ecommerce.dto.VendaDTO;
import com.EdYass.ecommerce.entity.Produto;
import com.EdYass.ecommerce.entity.Venda;
import com.EdYass.ecommerce.exception.ProductNotFoundException;
import com.EdYass.ecommerce.exception.SaleNotFoundException;
import com.EdYass.ecommerce.repository.ProdutoRepository;
import com.EdYass.ecommerce.repository.VendaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendaService {
    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Venda> getAllVendas() {
        return vendaRepository.findAll();
    }

    public Venda getVendaById(Long id) {
        return vendaRepository.findById(id)
                .orElseThrow(() -> new SaleNotFoundException("Venda não encontrada"));
    }

    @Transactional
    public Venda createVenda(VendaDTO vendaDTO) {
        List<Produto> produtos = vendaDTO.getProdutoIds().stream()
                .map(id -> produtoRepository.findById(id)
                        .orElseThrow(() -> new ProductNotFoundException("Produto não encontrado")))
                .collect(Collectors.toList());

        produtos.forEach(produto -> {
            if (!isProdutoAvailable(produto.getId(), 1)) {
                throw new RuntimeException("Estoque insuficiente para o produto: " + produto.getNome());
            }
            produto.setStock(produto.getStock() - 1);
            produtoRepository.save(produto);
        });

        Venda venda = new Venda();
        venda.setData(vendaDTO.getData());
        venda.setProdutos(produtos);
        return vendaRepository.save(venda);
    }

    @Transactional
    public Venda updateVenda(Long id, VendaDTO vendaDTO) {
        Venda venda = getVendaById(id);
        List<Produto> produtos = vendaDTO.getProdutoIds().stream()
                .map(produtoId -> produtoRepository.findById(produtoId)
                        .orElseThrow(() -> new ProductNotFoundException("Produto não encontrado")))
                .collect(Collectors.toList());

        venda.setProdutos(produtos);
        venda.setData(vendaDTO.getData());
        return vendaRepository.save(venda);
    }

    @Transactional
    public void deleteVenda(Long id) {
        Venda venda = getVendaById(id);
        vendaRepository.delete(venda);
    }

    public List<Venda> filterVendasByDate(LocalDateTime startDate, LocalDateTime endDate) {
        return vendaRepository.findAll().stream()
                .filter(venda -> venda.getData().isAfter(startDate) && venda.getData().isBefore(endDate))
                .collect(Collectors.toList());
    }

    public List<Venda> getVendasReport(LocalDateTime startDate, LocalDateTime endDate) {
        return filterVendasByDate(startDate, endDate);
    }

    private boolean isProdutoAvailable(Long id, int quantity) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Produto não encontrado"));
        return produto.getStock() >= quantity;
    }
}
