package com.EdYass.ecommerce.controller;

import com.EdYass.ecommerce.dto.VendaDTO;
import com.EdYass.ecommerce.entity.Venda;
import com.EdYass.ecommerce.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class VendaController {
    @Autowired
    private VendaService saleService;

    @GetMapping
    public List<Venda> getAllSales() {
        return saleService.getAllVendas();
    }

    @GetMapping("/{id}")
    public Venda getSaleById(@PathVariable Long id) {
        return saleService.getVendaById(id);
    }

    @PostMapping
    public Venda createSale(@RequestBody VendaDTO vendaDTO) {
        return saleService.createVenda(vendaDTO);
    }

    @PutMapping("/{id}")
    public Venda updateSale(@PathVariable Long id, @RequestBody VendaDTO vendaDTO) {
        return saleService.updateVenda(id, vendaDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable Long id) {
        saleService.deleteVenda(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filter")
    public List<Venda> filterVendasByDate(@RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate) {
        return saleService.filterVendasByDate(startDate, endDate);
    }

    @GetMapping("/report")
    public List<Venda> getVendasReport(@RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate) {
        return saleService.getVendasReport(startDate, endDate);
    }
}
