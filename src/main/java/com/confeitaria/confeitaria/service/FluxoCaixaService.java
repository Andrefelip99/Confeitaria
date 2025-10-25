package com.confeitaria.confeitaria.service;


import com.confeitaria.confeitaria.model.FluxoCaixa;
import com.confeitaria.confeitaria.repository.FluxoCaixaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FluxoCaixaService {

    private final FluxoCaixaRepository repository;

    public FluxoCaixaService(FluxoCaixaRepository repository) {
        this.repository = repository;
    }

    public List<FluxoCaixa> findAll() {
        return repository.findAll();
    }

    public Optional<FluxoCaixa> findById(Integer id) {
        return repository.findById(id);
    }

    public FluxoCaixa save(FluxoCaixa fluxo) {
        return repository.save(fluxo);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

   
    public List<FluxoCaixa> filtrarPorMes(LocalDate mes) {
        return repository.findAll().stream()
                .filter(f -> f.getMes().getMonth() == mes.getMonth() &&
                             f.getMes().getYear() == mes.getYear())
                .collect(Collectors.toList());
    }

   
}
