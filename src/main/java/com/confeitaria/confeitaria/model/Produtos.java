package com.confeitaria.confeitaria.model;



import java.math.BigDecimal;
import java.math.RoundingMode;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "produtos")
public class Produtos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String sabor;

    private String ingredientes;

    @Column(nullable = false)
    private BigDecimal quantidadeIngredientes = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);

    public Produtos() {
    }

    public Produtos(String sabor, String ingredientes, BigDecimal quantidadeIngredientes) {
        this.sabor = sabor;
        this.ingredientes = ingredientes;
        this.quantidadeIngredientes = (quantidadeIngredientes != null)
                ? quantidadeIngredientes.setScale(2, RoundingMode.HALF_UP)
                : BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
    }

    public void adicionarProduto() {
        System.out.println("Produto adicionado: " + this.sabor);
    }

    public void atualizarIngredientes(String novosIngredientes, BigDecimal novaQuantidade) {
        this.ingredientes = novosIngredientes;
        this.quantidadeIngredientes = (novaQuantidade != null)
                ? novaQuantidade.setScale(2, RoundingMode.HALF_UP)
                : BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        System.out.println("Ingredientes atualizados para: " + this.ingredientes +
                " | Quantidade: " + this.quantidadeIngredientes);
    }

    public void listarProduto() {
        System.out.println("ID: " + id);
        System.out.println("Sabor: " + sabor);
        System.out.println("Ingredientes: " + ingredientes);
        System.out.println("Quantidade de Ingredientes: " + quantidadeIngredientes);
    }

    @Override
    public String toString() {
        return "Produtos{" +
                "id=" + id +
                ", sabor='" + sabor + '\'' +
                ", ingredientes='" + ingredientes + '\'' +
                ", quantidadeIngredientes=" + quantidadeIngredientes +
                '}';
    }
}
