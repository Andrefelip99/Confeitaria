package com.confeitaria.confeitaria.service;


import com.confeitaria.confeitaria.model.DespesasInvestimento;
import com.confeitaria.confeitaria.repository.DespesasInvestimentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DespesasInvestimentoService {

    private final DespesasInvestimentoRepository repository;

    public DespesasInvestimentoService(DespesasInvestimentoRepository repository) {
        this.repository = repository;
    }

    public List<DespesasInvestimento> findAll() {
        return repository.findAll();
    }

    public Optional<DespesasInvestimento> findById(Integer id) {
        return repository.findById(id);
    }

    public DespesasInvestimento save(DespesasInvestimento investimento) {
        return repository.save(investimento);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
