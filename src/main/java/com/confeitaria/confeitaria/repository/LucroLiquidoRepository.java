package com.confeitaria.confeitaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.confeitaria.confeitaria.model.LucroLiquido;

@Repository
public interface LucroLiquidoRepository extends JpaRepository<LucroLiquido, Integer> {
    
}
