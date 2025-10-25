package com.confeitaria.confeitaria.service;



import com.confeitaria.confeitaria.model.VendasDiarias;
import com.confeitaria.confeitaria.repository.VendasDiariasRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class VendasDiariasService {

    private final VendasDiariasRepository repository;

    public VendasDiariasService(VendasDiariasRepository repository) {
        this.repository = repository;
    }

    public List<VendasDiarias> findAll() {
        return repository.findAll();
    }

    public Optional<VendasDiarias> findById(Integer id) {
        return repository.findById(id);
    }

    public VendasDiarias save(VendasDiarias vendaDiaria) {
        return repository.save(vendaDiaria);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

   
    public Optional<VendasDiarias> atualizarQuantidades(Integer id, BigDecimal vendida, BigDecimal perda, BigDecimal marketing) {
        return repository.findById(id).map(venda -> {
            if (vendida != null) venda.setQuantidadeVendida(vendida);
            if (perda != null) venda.setQuantidadePerda(perda);
            if (marketing != null) venda.setQuantidadeMarketing(marketing);
            venda.calcularQuantidadeTotal();
            return repository.save(venda);
        });
    }
}

