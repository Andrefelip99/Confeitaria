package com.confeitaria.confeitaria.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.confeitaria.confeitaria.model.FluxoCaixa;

@Repository
public interface FluxoCaixaRepository extends JpaRepository<FluxoCaixa, Integer> {
    
}
