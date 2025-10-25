package com.confeitaria.confeitaria.controller;

import com.confeitaria.confeitaria.model.Vendas;
import com.confeitaria.confeitaria.service.VendasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vendas")
public class VendasController {

    @Autowired
    private VendasService vendasService;

    @PreAuthorize("hasAnyRole('ADMIN','FUNCIONARIO')")
    @GetMapping
    public ResponseEntity<List<Vendas>> listarTodas() {
        return ResponseEntity.ok(vendasService.findAll());
    }

    @PreAuthorize("hasAnyRole('ADMIN','FUNCIONARIO')")
    @PostMapping
    public ResponseEntity<Vendas> criar(@RequestBody Vendas venda) {
        Vendas novaVenda = vendasService.save(venda);
        return ResponseEntity.ok(novaVenda);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Vendas> atualizar(@PathVariable Integer id, @RequestBody Vendas vendaAtualizada) {
        Optional<Vendas> vendaExistente = vendasService.findById(id);
        if (vendaExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        vendaAtualizada.setId(id);
        Vendas vendaSalva = vendasService.save(vendaAtualizada);
        return ResponseEntity.ok(vendaSalva);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        if (vendasService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        vendasService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
