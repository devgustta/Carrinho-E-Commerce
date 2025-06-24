package com.example.CarrinhoEcommerce.repositorie;

import com.example.CarrinhoEcommerce.model.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProdutoRepositorie extends JpaRepository<ProdutoModel, UUID> {
}
