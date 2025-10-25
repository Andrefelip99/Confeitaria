package com.confeitaria.confeitaria.service;


import com.confeitaria.confeitaria.model.ResumoMensal;
import com.confeitaria.confeitaria.repository.ResumoMensalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResumoMensalService {

    private final ResumoMensalRepository repository;

    public ResumoMensal salvarResumo(ResumoMensal resumo) {
        return repository.save(resumo);
    }

    public Optional<ResumoMensal> buscarPorMes(YearMonth referencia) {
        return repository.findByReferencia(referencia);
    }

    public void deletarResumo(Integer id) {
        repository.deleteById(id);
    }
}
