package com.confeitaria.confeitaria.service;



import com.confeitaria.confeitaria.model.Vendas;
import com.confeitaria.confeitaria.repository.VendasRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class VendasService {

    private final VendasRepository repository;

    public VendasService(VendasRepository repository) {
        this.repository = repository;
    }

    public List<Vendas> findAll() {
        return repository.findAll();
    }

    public Optional<Vendas> findById(Integer id) {
        return repository.findById(id);
    }

    public Vendas save(Vendas venda) {
        return repository.save(venda);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }


    public Optional<Vendas> atualizarQuantidade(Integer id, BigDecimal novaQuantidade) {
        return repository.findById(id).map(venda -> {
            venda.setQuantidadeVendida(novaQuantidade);
            return repository.save(venda);
        });
    }
}
