package com.confeitaria.confeitaria.controller;


import com.confeitaria.confeitaria.model.DespesasInvestimento;
import com.confeitaria.confeitaria.service.DespesasInvestimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/despesas-investimento")
public class DespesasInvestimentoController {

    @Autowired
    private DespesasInvestimentoService despesasInvestimentoService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')") 
    public ResponseEntity<List<DespesasInvestimento>> listarTodos() {
        return ResponseEntity.ok(despesasInvestimentoService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DespesasInvestimento> buscarPorId(@PathVariable Integer id) {
        Optional<DespesasInvestimento> investimento = despesasInvestimentoService.findById(id);
        return investimento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','FUNCIONARIO')") 
    public ResponseEntity<DespesasInvestimento> criar(@RequestBody DespesasInvestimento investimento) {
        DespesasInvestimento novoInvestimento = despesasInvestimentoService.save(investimento);
        return ResponseEntity.ok(novoInvestimento);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','FUNCIONARIO')")
    public ResponseEntity<DespesasInvestimento> atualizar(@PathVariable Integer id, @RequestBody DespesasInvestimento investimentoAtualizado) {
        Optional<DespesasInvestimento> investimentoExistente = despesasInvestimentoService.findById(id);
        if (investimentoExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        investimentoAtualizado.setId(id);
        DespesasInvestimento investimentoSalvo = despesasInvestimentoService.save(investimentoAtualizado);
        return ResponseEntity.ok(investimentoSalvo);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')") 
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        if (despesasInvestimentoService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        despesasInvestimentoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
