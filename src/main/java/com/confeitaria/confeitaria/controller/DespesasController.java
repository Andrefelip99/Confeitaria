package com.confeitaria.confeitaria.controller;


import com.confeitaria.confeitaria.model.Despesas;
import com.confeitaria.confeitaria.service.DespesasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/despesas")
public class DespesasController {

    @Autowired
    private DespesasService despesasService;

    @PreAuthorize("hasAnyRole('ADMIN','FUNCIONARIO')")
    @GetMapping
    public ResponseEntity<List<Despesas>> listarTodas() {
        return ResponseEntity.ok(despesasService.findAll());
    }

    @PreAuthorize("hasAnyRole('ADMIN','FUNCIONARIO')")
    @PostMapping
    public ResponseEntity<Despesas> criar(@RequestBody Despesas despesa) {
        Despesas novaDespesa = despesasService.save(despesa);
        return ResponseEntity.ok(novaDespesa);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Despesas> atualizar(@PathVariable Integer id, @RequestBody Despesas despesaAtualizada) {
        Optional<Despesas> despesaExistente = despesasService.findById(id);
        if (despesaExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        despesaAtualizada.setId(id);
        Despesas despesaSalva = despesasService.save(despesaAtualizada);
        return ResponseEntity.ok(despesaSalva);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        if (despesasService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        despesasService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

