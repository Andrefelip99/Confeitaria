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
@Table(name = "vendas")
public class Vendas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String produtoVendido;

    @Column(nullable = false)
    private BigDecimal quantidadeVendida = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);

    @Column(nullable = false)
    private LocalDate dataVendida;

    public Vendas() {
    }

    public Vendas(String produtoBolo, BigDecimal quantidadeVendida, LocalDate dataVendida) {
        this.produtoVendido = produtoBolo;
        this.quantidadeVendida = (quantidadeVendida != null)
                ? quantidadeVendida.setScale(2, RoundingMode.HALF_UP)
                : BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        this.dataVendida = dataVendida;
    }

    public void registrarVenda() {
        System.out.println("Venda registrada: " + produtoVendido + 
                " | Quantidade: " + quantidadeVendida + 
                " | Data: " + dataVendida);
    }

    public void atualizarQuantidade(BigDecimal novaQuantidade) {
        if (novaQuantidade != null) {
            this.quantidadeVendida = novaQuantidade.setScale(2, RoundingMode.HALF_UP);
            System.out.println("Quantidade atualizada para: " + quantidadeVendida);
        }
    }

    @Override
    public String toString() {
        return "Vendas{" +
                "id=" + id +
                ", produtoBolo='" + produtoVendido + '\'' +
                ", quantidadeVendida=" + quantidadeVendida +
                ", dataVendida=" + dataVendida +
                '}';
    }
}
