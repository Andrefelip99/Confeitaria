package com.confeitaria.confeitaria.service;


import com.confeitaria.confeitaria.model.Despesas;
import com.confeitaria.confeitaria.repository.DespesasRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DespesasService {

    private final DespesasRepository repository;

    public DespesasService(DespesasRepository repository) {
        this.repository = repository;
    }

    public List<Despesas> findAll() {
        return repository.findAll();
    }

    public Optional<Despesas> findById(Integer id) {
        return repository.findById(id);
    }

    public Despesas save(Despesas despesa) {
        return repository.save(despesa);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public List<Despesas> filtrarPorMes(LocalDate mes) {
        return repository.findAll().stream()
                .filter(d -> d.getMes().getMonth() == mes.getMonth() &&
                             d.getMes().getYear() == mes.getYear())
                .collect(Collectors.toList());
    }
}
