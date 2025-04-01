package com.th_oliveira.spring_boot_project.entity.produto;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_produtos")
public class ProdutoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="nome")
    private String nome;

    @Column(name="preco")
    private Double preco;

    public ProdutoEntity(){}

    public ProdutoEntity(String nome, Double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}