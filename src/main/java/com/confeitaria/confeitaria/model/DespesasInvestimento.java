package com.confeitaria.confeitaria.model;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "despesas_investimento")
public class DespesasInvestimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private LocalDate mes;

    @Column(nullable = false)
    private String tipoInvestimento;

    @Column(nullable = false)
    private BigDecimal valor = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);

    public DespesasInvestimento() {
    }

    public DespesasInvestimento(LocalDate mes, String tipoInvestimento, BigDecimal valor) {
        this.mes = mes;
        this.tipoInvestimento = tipoInvestimento;
        this.valor = (valor != null)
                ? valor.setScale(2, RoundingMode.HALF_UP)
                : BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
    }

    public void adicionarValor(BigDecimal valorAdicional) {
        if (valorAdicional != null) {
            this.valor = this.valor.add(valorAdicional.setScale(2, RoundingMode.HALF_UP));
        }
    }

    public void atualizarValor(BigDecimal novoValor) {
        if (novoValor != null) {
            this.valor = novoValor.setScale(2, RoundingMode.HALF_UP);
        }
    }

    @Override
    public String toString() {
        return "DespesasInvestimento{" +
                "id=" + id +
                ", mes=" + mes +
                ", tipoInvestimento='" + tipoInvestimento + '\'' +
                ", valor=" + valor +
                '}';
    }
}
