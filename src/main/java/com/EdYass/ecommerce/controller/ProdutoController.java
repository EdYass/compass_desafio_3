package com.EdYass.ecommerce.controller;

import com.EdYass.ecommerce.dto.ProdutoDTO;
import com.EdYass.ecommerce.entity.Produto;
import com.EdYass.ecommerce.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProdutoController {
    @Autowired
    private ProdutoService productService;

    @GetMapping
    public List<Produto> getAllProducts() {
        return productService.getAllProdutos();
    }

    @GetMapping("/{id}")
    public Produto getProductById(@PathVariable Long id) {
        return productService.getProdutoById(id);
    }

    @PostMapping
    public Produto createProduct(@RequestBody ProdutoDTO produtoDTO) {
        return productService.createProduto(produtoDTO);
    }

    @PutMapping("/{id}")
    public Produto updateProduct(@PathVariable Long id, @RequestBody ProdutoDTO produtoDTO) {
        return productService.updateProduto(id, produtoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduto(id);
        return ResponseEntity.noContent().build();
    }
}
