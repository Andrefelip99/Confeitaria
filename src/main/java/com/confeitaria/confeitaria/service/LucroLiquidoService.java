package com.confeitaria.confeitaria.service;


import com.confeitaria.confeitaria.model.LucroLiquido;
import com.confeitaria.confeitaria.repository.LucroLiquidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LucroLiquidoService {

    private final LucroLiquidoRepository repository;

    public LucroLiquidoService(LucroLiquidoRepository repository) {
        this.repository = repository;
    }

    public List<LucroLiquido> findAll() {
        return repository.findAll();
    }

    public Optional<LucroLiquido> findById(Integer id) {
        return repository.findById(id);
    }

    public LucroLiquido save(LucroLiquido lucro) {
        return repository.save(lucro);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
