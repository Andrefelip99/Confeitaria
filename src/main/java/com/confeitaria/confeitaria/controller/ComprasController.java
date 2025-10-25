

package com.confeitaria.confeitaria.controller;

import com.confeitaria.confeitaria.model.Compras;
import com.confeitaria.confeitaria.service.ComprasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/compras")
public class ComprasController {

    @Autowired
    private ComprasService comprasService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')") 
    public ResponseEntity<List<Compras>> listarTodas() {
        return ResponseEntity.ok(comprasService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')") 
    public ResponseEntity<Compras> buscarPorId(@PathVariable Integer id) {
        Optional<Compras> compra = comprasService.findById(id);
        return compra.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','FUNCIONARIO')") 
    public ResponseEntity<Compras> criar(@RequestBody Compras compra) {
        Compras novaCompra = comprasService.save(compra);
        return ResponseEntity.ok(novaCompra);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','FUNCIONARIO')") 
    public ResponseEntity<Compras> atualizar(@PathVariable Integer id, @RequestBody Compras compraAtualizada) {
        Optional<Compras> compraExistente = comprasService.findById(id);
        if (compraExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        compraAtualizada.setId(id);
        Compras compraSalva = comprasService.save(compraAtualizada);
        return ResponseEntity.ok(compraSalva);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')") 
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        if (comprasService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        comprasService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
