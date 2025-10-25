package com.confeitaria.confeitaria.repository;

import com.confeitaria.confeitaria.model.ResumoMensal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.YearMonth;
import java.util.Optional;

public interface ResumoMensalRepository extends JpaRepository<ResumoMensal, Integer> {
    Optional<ResumoMensal> findByReferencia(YearMonth referencia);
}
