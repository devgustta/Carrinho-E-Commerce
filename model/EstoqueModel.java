package com.example.CarrinhoEcommerce.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "TB_ESTOQUE")
public class EstoqueModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // tipo de geração de id "Auto"
    private UUID id;

    private int qtd;

    private String tipo; // tipo de operação, se é uma entrada no estoque ou saida

    @OneToMany(mappedBy = "estoque", cascade = CascadeType.ALL)
    private Set<ProdutoModel> produto; // lista de todos os produtos cadastrados no estoque


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Set<ProdutoModel> getProduto() {
        return produto;
    }

    public void setProduto(Set<ProdutoModel> produto) {
        this.produto = produto;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
