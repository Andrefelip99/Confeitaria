package com.confeitaria.confeitaria.model;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "lucro_liquido")
@Getter
@Setter
public class LucroLiquido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private LocalDate mes;

    @Column(nullable = false)
    private BigDecimal lucro = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);

    private String descricao;

    public LucroLiquido() {
    }

    public LucroLiquido(LocalDate mes, BigDecimal lucro, String descricao) {
        this.mes = mes;
        this.lucro = (lucro != null) ? lucro.setScale(2, RoundingMode.HALF_UP) : BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        this.descricao = descricao;
    }

    public void calcularLucro(BigDecimal receita, BigDecimal despesas, BigDecimal custosVariaveis, BigDecimal despesasInvestimento) {
        receita = Objects.requireNonNullElse(receita, BigDecimal.ZERO).setScale(2, RoundingMode.HALF_UP);
        despesas = Objects.requireNonNullElse(despesas, BigDecimal.ZERO).setScale(2, RoundingMode.HALF_UP);
        custosVariaveis = Objects.requireNonNullElse(custosVariaveis, BigDecimal.ZERO).setScale(2, RoundingMode.HALF_UP);
        despesasInvestimento = Objects.requireNonNullElse(despesasInvestimento, BigDecimal.ZERO).setScale(2, RoundingMode.HALF_UP);

        this.lucro = receita.subtract(despesas)
                            .subtract(custosVariaveis)
                            .subtract(despesasInvestimento)
                            .setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String toString() {
        return "LucroLiquido{" +
                "id=" + id +
                ", mes=" + mes +
                ", lucro=" + lucro +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
