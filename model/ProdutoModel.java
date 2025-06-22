package com.example.CarrinhoEcommerce.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "TB_PRODUTO")
public class ProdutoModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // tipo de geração de id "Auto"
    private UUID id;

    private String name;

    private float preco;

    @ManyToMany
    @JoinTable(
            name = "tb_carrinho_produto",
            joinColumns = @JoinColumn(name = "produto_id"),
            inverseJoinColumns = @JoinColumn(name = "carrinho_id")
    )
    Set<CarrinhoModel> carrinho = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "estoque_id")
    EstoqueModel estoque;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }
}
