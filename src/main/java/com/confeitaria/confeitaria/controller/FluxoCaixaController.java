package com.confeitaria.confeitaria.controller;



import com.confeitaria.confeitaria.model.FluxoCaixa;
import com.confeitaria.confeitaria.service.FluxoCaixaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;



import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fluxo-caixa")
public class FluxoCaixaController {

    @Autowired
    private FluxoCaixaService fluxoCaixaService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<FluxoCaixa>> listarTodas() {
        return ResponseEntity.ok(fluxoCaixaService.findAll());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<FluxoCaixa> buscarPorId(@PathVariable Integer id) {
        Optional<FluxoCaixa> transacao = fluxoCaixaService.findById(id);
        return transacao.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<FluxoCaixa> criar(@RequestBody FluxoCaixa transacao) {
        FluxoCaixa novaTransacao = fluxoCaixaService.save(transacao);
        return ResponseEntity.ok(novaTransacao);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<FluxoCaixa> atualizar(@PathVariable Integer id, @RequestBody FluxoCaixa transacaoAtualizada) {
        Optional<FluxoCaixa> transacaoExistente = fluxoCaixaService.findById(id);
        if (transacaoExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        transacaoAtualizada.setId(id);
        FluxoCaixa transacaoSalva = fluxoCaixaService.save(transacaoAtualizada);
        return ResponseEntity.ok(transacaoSalva);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        if (fluxoCaixaService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        fluxoCaixaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/mes")
    public ResponseEntity<List<FluxoCaixa>> filtrarPorMes(@RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate mes) {
        List<FluxoCaixa> transacoesFiltradas = fluxoCaixaService.filtrarPorMes(mes);
        return ResponseEntity.ok(transacoesFiltradas);
    }




}
