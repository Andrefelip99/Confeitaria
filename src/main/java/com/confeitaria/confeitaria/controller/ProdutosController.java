package com.confeitaria.confeitaria.controller;

import com.confeitaria.confeitaria.model.Produtos;
import com.confeitaria.confeitaria.service.ProdutosServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutosController {

    @Autowired
    private ProdutosServices produtosService;

    @PreAuthorize("hasAnyRole('ADMIN','FUNCIONARIO')")
    @GetMapping
    public ResponseEntity<List<Produtos>> listarTodos() {
        return ResponseEntity.ok(produtosService.findAll());
    }

    @PreAuthorize("hasAnyRole('ADMIN','FUNCIONARIO')")
    @PostMapping
    public ResponseEntity<Produtos> criar(@RequestBody Produtos produto) {
        Produtos novoProduto = produtosService.save(produto);
        return ResponseEntity.ok(novoProduto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Produtos> atualizar(@PathVariable Integer id, @RequestBody Produtos produtoAtualizado) {
        Optional<Produtos> produtoExistente = produtosService.findById(id);
        if (produtoExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        produtoAtualizado.setId(id);
        Produtos produtoSalvo = produtosService.save(produtoAtualizado);
        return ResponseEntity.ok(produtoSalvo);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        if (produtosService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        produtosService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
