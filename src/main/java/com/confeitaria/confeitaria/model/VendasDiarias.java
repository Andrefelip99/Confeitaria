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
@Table(name = "vendasDiarias")
public class VendasDiarias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String produtoBolo;

    @Column(nullable = false)
    private BigDecimal quantidadeVendida = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);

    @Column(nullable = false)
    private BigDecimal quantidadePerda = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);

    @Column(nullable = false)
    private BigDecimal quantidadeMarketing = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);

    @Column(nullable = false)
    private BigDecimal quantidadeTotal = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);

    @Column(nullable = false)
    private LocalDate dataVenda;

    public VendasDiarias() {
    }

    public VendasDiarias(String produtoBolo, BigDecimal quantidadeVendida,
                         BigDecimal quantidadePerda, BigDecimal quantidadeMarketing, LocalDate dataVenda) {
        this.produtoBolo = produtoBolo;
        this.quantidadeVendida = (quantidadeVendida != null) ? quantidadeVendida.setScale(2, RoundingMode.HALF_UP) : BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        this.quantidadePerda = (quantidadePerda != null) ? quantidadePerda.setScale(2, RoundingMode.HALF_UP) : BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        this.quantidadeMarketing = (quantidadeMarketing != null) ? quantidadeMarketing.setScale(2, RoundingMode.HALF_UP) : BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        this.dataVenda = dataVenda;
        calcularQuantidadeTotal();
    }

    public void calcularQuantidadeTotal() {
        this.quantidadeTotal = quantidadeVendida
                .add(quantidadePerda)
                .add(quantidadeMarketing)
                .setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String toString() {
        return "VendasDiarias{" +
                "id=" + id +
                ", produtoBolo='" + produtoBolo + '\'' +
                ", quantidadeVendida=" + quantidadeVendida +
                ", quantidadePerda=" + quantidadePerda +
                ", quantidadeMarketing=" + quantidadeMarketing +
                ", quantidadeTotal=" + quantidadeTotal +
                ", dataVenda=" + dataVenda +
                '}';
    }
}
