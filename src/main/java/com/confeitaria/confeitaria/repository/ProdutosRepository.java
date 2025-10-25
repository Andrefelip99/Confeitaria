package com.confeitaria.confeitaria.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.confeitaria.confeitaria.model.Produtos;

@Repository
public interface ProdutosRepository extends JpaRepository<Produtos, Integer> {
}
