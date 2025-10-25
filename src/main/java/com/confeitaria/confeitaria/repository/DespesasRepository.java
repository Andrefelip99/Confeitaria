package com.confeitaria.confeitaria.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.confeitaria.confeitaria.model.Despesas;

@Repository
public interface DespesasRepository extends JpaRepository<Despesas, Integer> {
}
