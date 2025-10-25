package com.confeitaria.confeitaria.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.YearMonth;
import java.util.List;

@Entity
@Table(name = "resumo_mensal")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResumoMensal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private YearMonth referencia;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "resumo_id")
    private List<FluxoCaixa> transacoes;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "resumo_id")
    private List<Compras> compras;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "resumo_id")
    private List<Despesas> despesas;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "resumo_id")
    private List<CustosVariaveis> custosVariaveis;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "resumo_id")
    private List<DespesasInvestimento> investimentos;

    @OneToOne(cascade = CascadeType.ALL)
    private LucroLiquido lucro;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "resumo_id")
    private List<Vendas> vendas;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "resumo_id")
    private List<VendasDiarias> vendasDiarias;
}
