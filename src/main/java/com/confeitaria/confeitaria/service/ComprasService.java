package com.confeitaria.confeitaria.service;

import com.confeitaria.confeitaria.model.Compras;
import com.confeitaria.confeitaria.repository.ComprasRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComprasService {

    private final ComprasRepository comprasRepository;

    public ComprasService(ComprasRepository comprasRepository) {
        this.comprasRepository = comprasRepository;
    }

    public List<Compras> findAll() {
        return comprasRepository.findAll();
    }

    public Optional<Compras> findById(Integer id) {
        return comprasRepository.findById(id);
    }

    public Compras save(Compras compra) {
        return comprasRepository.save(compra);
    }

    public void deleteById(Integer id) {
        comprasRepository.deleteById(id);
    }
}
