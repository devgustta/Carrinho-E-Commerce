package com.example.CarrinhoEcommerce.repositorie;

import com.example.CarrinhoEcommerce.model.EstoqueModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EstoqueRepositorie extends JpaRepository<EstoqueModel, UUID> {


}
