package com.confeitaria.confeitaria.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.confeitaria.confeitaria.model.Compras;

@Repository
public interface ComprasRepository extends JpaRepository<Compras, Integer> {
    
}
