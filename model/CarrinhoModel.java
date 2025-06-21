package com.example.CarrinhoEcommerce.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "TB_CARRINHO")
public class CarrinhoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // tipo de geração de id "Auto"
    private UUID id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(mappedBy = "carrinho", fetch = FetchType.LAZY)
    private Set<ProdutoModel> produto = new HashSet<>();

    @OneToOne(mappedBy = "carrinhoModel")
    private User user;

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

}
