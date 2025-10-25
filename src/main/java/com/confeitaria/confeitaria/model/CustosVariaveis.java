package com.confeitaria.confeitaria.model;


import java.time.LocalDate;
import java.math.BigDecimal;
import java.math.RoundingMode;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "custos_variaveis")
public class CustosVariaveis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    
    @Column(nullable = false)
    private LocalDate mes;

    @Column(nullable = false)
    private String fornecedor;

    @Column(nullable = false)
    private BigDecimal custo = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);

    private String descricao;

    public CustosVariaveis() {
    }

    public CustosVariaveis(LocalDate mes, String fornecedor, BigDecimal custo, String descricao) {
        this.mes = mes;
        this.fornecedor = fornecedor;
        this.custo = (custo != null)
                ? custo.setScale(2, RoundingMode.HALF_UP)
                : BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        this.descricao = descricao;
    }

    public void adicionarValor(BigDecimal valorAdicional) {
        if (valorAdicional != null) {
            this.custo = this.custo.add(valorAdicional.setScale(2, RoundingMode.HALF_UP));
        }
    }

    public void atualizarValor(BigDecimal novoCusto) {
        if (novoCusto != null) {
            this.custo = novoCusto.setScale(2, RoundingMode.HALF_UP);
        }
    }

    @Override
    public String toString() {
        return "CustosVariaveis{" +
                "id=" + id +
                ", mes=" + mes +
                ", fornecedor='" + fornecedor + '\'' +
                ", custo=" + custo +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
