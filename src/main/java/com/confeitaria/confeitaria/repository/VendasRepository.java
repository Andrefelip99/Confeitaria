package com.confeitaria.confeitaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.confeitaria.confeitaria.model.Vendas;

@Repository
public interface VendasRepository extends JpaRepository<Vendas, Integer> {
   
}
