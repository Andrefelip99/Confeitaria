package com.confeitaria.confeitaria.controller;

import com.confeitaria.confeitaria.model.ResumoMensal;
import com.confeitaria.confeitaria.service.ResumoMensalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;
import java.util.Optional;

@RestController
@RequestMapping("/api/resumo-mensal")
@RequiredArgsConstructor
public class ResumoMensalController {

    private final ResumoMensalService service;

    @PostMapping
    public ResumoMensal criarResumo(@RequestBody ResumoMensal resumo) {
        return service.salvarResumo(resumo);
    }

    @GetMapping("/{ano}/{mes}")
    public Optional<ResumoMensal> buscarPorMes(@PathVariable int ano, @PathVariable int mes) {
        return service.buscarPorMes(YearMonth.of(ano, mes));
    }

    @DeleteMapping("/{id}")
    public void deletarResumo(@PathVariable Integer id) {
        service.deletarResumo(id);
    }
}
