package com.EdYass.ecommerce.service;

import com.EdYass.ecommerce.dto.ProdutoDTO;
import com.EdYass.ecommerce.entity.Produto;
import com.EdYass.ecommerce.entity.ProdutoStatus;
import com.EdYass.ecommerce.exception.ProductInUseException;
import com.EdYass.ecommerce.exception.ProductNotFoundException;
import com.EdYass.ecommerce.repository.ProdutoRepository;
import com.EdYass.ecommerce.repository.VendaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private VendaRepository vendaRepository;

    public List<Produto> getAllProdutos() {
        return produtoRepository.findAll();
    }

    public Produto getProdutoById(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Produto não encontrado"));
    }

    @Transactional
    public Produto createProduto(ProdutoDTO produtoDTO) {
        Produto produto = new Produto();
        produto.setNome(produtoDTO.getNome());
        produto.setDescricao(produtoDTO.getDescricao());
        produto.setPreco(produtoDTO.getPreco());
        produto.setStock(produtoDTO.getStock());
        produto.setStatus(ProdutoStatus.ACTIVE);
        return produtoRepository.save(produto);
    }

    @Transactional
    public Produto updateProduto(Long id, ProdutoDTO produtoDTO) {
        Produto produto = getProdutoById(id);
        produto.setNome(produtoDTO.getNome());
        produto.setDescricao(produtoDTO.getDescricao());
        produto.setPreco(produtoDTO.getPreco());
        produto.setStock(produtoDTO.getStock());
        return produtoRepository.save(produto);
    }

    @Transactional
    public void deleteProduto(Long id) {
        Produto produto = getProdutoById(id);
        if (vendaRepository.existsByProdutosId(id)) {
            throw new ProductInUseException("Produto já está inserido em uma venda e não pode ser excluído.");
        }
        else {
            produtoRepository.delete(produto);
        }
    }

}
