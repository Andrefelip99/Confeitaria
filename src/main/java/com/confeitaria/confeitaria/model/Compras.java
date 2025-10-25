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
@Table(name = "compras")
public class Compras {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String produto;

    @Column(nullable = false)
    private String fornecedor;

    private BigDecimal media = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);

    @Column(nullable = false)
    private BigDecimal quantidadeComprada;

    @Column(nullable = false)
    private BigDecimal valorUnitario;

    @Column(nullable = false)
    private BigDecimal valorFinal = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);

    @Column(name = "data_compra")
    private LocalDate data;

    
    public Compras() {
    }

    public Compras(String produto, String fornecedor, BigDecimal media,
                   BigDecimal quantidadeComprada, BigDecimal valorUnitario, LocalDate data) {
        this.produto = produto;
        this.fornecedor = fornecedor;
        this.media = (media != null)
                ? media.setScale(2, RoundingMode.HALF_UP)
                : BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        this.quantidadeComprada = quantidadeComprada;
        this.valorUnitario = valorUnitario;
        this.data = data;
        calculaValorFinal();
    }

    public void calculaValorFinal() {
        if (quantidadeComprada != null && valorUnitario != null) {
            this.valorFinal = quantidadeComprada
                    .multiply(valorUnitario)
                    .setScale(2, RoundingMode.HALF_UP);
        } else {
            this.valorFinal = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        }
    }

    @PrePersist
    @PreUpdate
    public void preSave() {
        calculaValorFinal();
    }


    @Override
    public String toString() {
        return "Compras [id=" + id +
               ", produto=" + produto +
               ", fornecedor=" + fornecedor +
               ", media=" + media +
               ", quantidadeComprada=" + quantidadeComprada +
               ", valorUnitario=" + valorUnitario +
               ", valorFinal=" + valorFinal +
               ", data=" + data + "]";
    }
}
