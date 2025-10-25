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
@Table(name = "despesas")
public class Despesas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private LocalDate mes;

    @Column(nullable = false)
    private String tipoDespesas;

    @Column(nullable = false)
    private BigDecimal valor = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);

    private String descricao;

    public Despesas() {
    }

    public Despesas(LocalDate mes, String tipoDespesas, BigDecimal valor, String descricao) {
        this.mes = mes;
        this.tipoDespesas = tipoDespesas;
        this.valor = (valor != null)
                ? valor.setScale(2, RoundingMode.HALF_UP)
                : BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        this.descricao = descricao;
    }

    public void registrarDespesa(BigDecimal valor) {
        if (valor != null) {
            this.valor = this.valor.add(valor.setScale(2, RoundingMode.HALF_UP));
        }
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

    public String resumo() {
        return String.format("[%s] %s: R$ %.2f", mes, tipoDespesas, valor.doubleValue());
    }

    @Override
    public String toString() {
        return "Despesas{" +
                "id=" + id +
                ", mes=" + mes +
                ", tipoDespesas='" + tipoDespesas + '\'' +
                ", valor=" + valor +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
