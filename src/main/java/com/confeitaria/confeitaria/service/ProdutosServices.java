package com.confeitaria.confeitaria.service;

import com.confeitaria.confeitaria.model.Produtos;
import com.confeitaria.confeitaria.repository.ProdutosRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutosServices {

    private final ProdutosRepository repository;

    public ProdutosServices(ProdutosRepository repository) {
        this.repository = repository;
    }

    public List<Produtos> findAll() {
        return repository.findAll();
    }

    public Optional<Produtos> findById(Integer id) {
        return repository.findById(id);
    }

    public Produtos save(Produtos produto) {
        return repository.save(produto);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public Optional<Produtos> atualizarIngredientes(Integer id, String novosIngredientes, BigDecimal novaQuantidade) {
        return repository.findById(id).map(produto -> {
            produto.setIngredientes(novosIngredientes);
            produto.setQuantidadeIngredientes(novaQuantidade);
            return repository.save(produto);
        });
    }
}
