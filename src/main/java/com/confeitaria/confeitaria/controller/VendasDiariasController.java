package com.confeitaria.confeitaria.controller;


import com.confeitaria.confeitaria.model.VendasDiarias;
import com.confeitaria.confeitaria.service.VendasDiariasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vendas-diarias")
public class VendasDiariasController {

    @Autowired
    private VendasDiariasService vendasDiariasService;

    @PreAuthorize("hasAnyRole('ADMIN','FUNCIONARIO')")
    @GetMapping
    public ResponseEntity<List<VendasDiarias>> listarTodas() {
        return ResponseEntity.ok(vendasDiariasService.findAll());
    }

    @PreAuthorize("hasAnyRole('ADMIN','FUNCIONARIO')")
    @PostMapping
    public ResponseEntity<VendasDiarias> criar(@RequestBody VendasDiarias venda) {
        VendasDiarias novaVenda = vendasDiariasService.save(venda);
        return ResponseEntity.ok(novaVenda);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<VendasDiarias> atualizar(@PathVariable Integer id, @RequestBody VendasDiarias vendaAtualizada) {
        Optional<VendasDiarias> vendaExistente = vendasDiariasService.findById(id);
        if (vendaExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        vendaAtualizada.setId(id);
        VendasDiarias vendaSalva = vendasDiariasService.save(vendaAtualizada);
        return ResponseEntity.ok(vendaSalva);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        if (vendasDiariasService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        vendasDiariasService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
