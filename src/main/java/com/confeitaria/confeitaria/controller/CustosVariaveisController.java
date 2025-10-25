package com.confeitaria.confeitaria.controller;


import com.confeitaria.confeitaria.model.CustosVariaveis;
import com.confeitaria.confeitaria.service.CustosVariaveisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/custos-variaveis")
public class CustosVariaveisController {

    @Autowired
    private CustosVariaveisService custosService;

    @PreAuthorize("hasAnyRole('ADMIN','FUNCIONARIO')")
    @GetMapping
    public ResponseEntity<List<CustosVariaveis>> listarTodos() {
        return ResponseEntity.ok(custosService.findAll());
    }

    @PreAuthorize("hasAnyRole('ADMIN','FUNCIONARIO')")
    @PostMapping
    public ResponseEntity<CustosVariaveis> criar(@RequestBody CustosVariaveis custo) {
        CustosVariaveis novoCusto = custosService.save(custo);
        return ResponseEntity.ok(novoCusto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<CustosVariaveis> atualizar(@PathVariable Integer id, @RequestBody CustosVariaveis custoAtualizado) {
        Optional<CustosVariaveis> custoExistente = custosService.findById(id);
        if (custoExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        custoAtualizado.setId(id);
        CustosVariaveis custoSalvo = custosService.save(custoAtualizado);
        return ResponseEntity.ok(custoSalvo);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        if (custosService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        custosService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
