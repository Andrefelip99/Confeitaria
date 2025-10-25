package com.confeitaria.confeitaria.service;

import com.confeitaria.confeitaria.model.CustosVariaveis;
import com.confeitaria.confeitaria.repository.CustosVariaveisRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustosVariaveisService {

    private final CustosVariaveisRepository repository;

    public CustosVariaveisService(CustosVariaveisRepository repository) {
        this.repository = repository;
    }

    public List<CustosVariaveis> findAll() {
        return repository.findAll();
    }

    public Optional<CustosVariaveis> findById(Integer id) {
        return repository.findById(id);
    }

    public CustosVariaveis save(CustosVariaveis custo) {
        return repository.save(custo);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
