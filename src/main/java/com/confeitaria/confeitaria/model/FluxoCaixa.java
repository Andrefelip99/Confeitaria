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
@Table(name = "fluxo_caixa")
public class FluxoCaixa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private LocalDate mes;

    @Column(nullable = false)
    private String tipoTransacao;

    @Column(nullable = false)
    private BigDecimal valor = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
    
    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private String categoria;

    @Column(nullable = false)
    private BigDecimal total = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);

    public FluxoCaixa() {
    }

    public FluxoCaixa(LocalDate mes, String tipoTransacao, BigDecimal valor, String descricao,
                      String categoria, BigDecimal total) {
        this.mes = mes;
        this.tipoTransacao = tipoTransacao;
        this.valor = (valor != null) ? valor.setScale(2, RoundingMode.HALF_UP) : BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        this.descricao = descricao;
        this.categoria = categoria;
        this.total = (total != null) ? total.setScale(2, RoundingMode.HALF_UP) : BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
    }

    public void registrarTransacao() {
        if ("ENTRADA".equalsIgnoreCase(tipoTransacao)) {
            System.out.printf("Entrada registrada: %s | Valor: R$ %.2f | Data: %s%n",
                    descricao, valor, mes);
        } else if ("SAÍDA".equalsIgnoreCase(tipoTransacao) || "SAIDA".equalsIgnoreCase(tipoTransacao)) {
            System.out.printf("Saída registrada: %s | Valor: R$ %.2f | Data: %s%n",
                    descricao, valor, mes);
        } else {
            System.out.println("Tipo de transação inválido: " + tipoTransacao);
        }
    }

    public void calcularTotalMensal(BigDecimal totalAnterior) {
        BigDecimal anterior = (totalAnterior != null) ? totalAnterior.setScale(2, RoundingMode.HALF_UP) : BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        BigDecimal atual = (valor != null) ? valor.setScale(2, RoundingMode.HALF_UP) : BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        this.total = anterior.add(atual).setScale(2, RoundingMode.HALF_UP);
        System.out.println("Total mensal atualizado: " + total);
    }

    @Override
    public String toString() {
        return "FluxoCaixa{" +
                "id=" + id +
                ", mes=" + mes +
                ", tipoTransacao='" + tipoTransacao + '\'' +
                ", valor=" + valor +
                ", descricao='" + descricao + '\'' +
                ", categoria='" + categoria + '\'' +
                ", total=" + total +
                '}';
    }
}
