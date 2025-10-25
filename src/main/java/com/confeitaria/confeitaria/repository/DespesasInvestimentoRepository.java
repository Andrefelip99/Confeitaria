package com.confeitaria.confeitaria.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.confeitaria.confeitaria.model.DespesasInvestimento;

@Repository
public interface DespesasInvestimentoRepository extends JpaRepository<DespesasInvestimento, Integer> {
}
