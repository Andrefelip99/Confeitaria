package com.confeitaria.confeitaria.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.confeitaria.confeitaria.model.VendasDiarias;

@Repository
public interface VendasDiariasRepository extends JpaRepository<VendasDiarias, Integer> {
    
}
