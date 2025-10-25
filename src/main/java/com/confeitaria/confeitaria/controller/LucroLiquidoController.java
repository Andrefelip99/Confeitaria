package com.confeitaria.confeitaria.controller;


import com.confeitaria.confeitaria.model.LucroLiquido;
import com.confeitaria.confeitaria.service.LucroLiquidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lucro-liquido")
public class LucroLiquidoController {

    @Autowired
    private LucroLiquidoService lucroService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<LucroLiquido>> listarTodos() {
        return ResponseEntity.ok(lucroService.findAll());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<LucroLiquido> criar(@RequestBody LucroLiquido lucro) {
        LucroLiquido novoLucro = lucroService.save(lucro);
        return ResponseEntity.ok(novoLucro);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<LucroLiquido> atualizar(@PathVariable Integer id, @RequestBody LucroLiquido lucroAtualizado) {
        Optional<LucroLiquido> lucroExistente = lucroService.findById(id);
        if (lucroExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        lucroAtualizado.setId(id);
        LucroLiquido lucroSalvo = lucroService.save(lucroAtualizado);
        return ResponseEntity.ok(lucroSalvo);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        if (lucroService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        lucroService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

