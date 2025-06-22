package com.example.CarrinhoEcommerce.controller;

import com.example.CarrinhoEcommerce.dto.ProdutoDTO;
import com.example.CarrinhoEcommerce.model.EstoqueModel;
import com.example.CarrinhoEcommerce.model.ProdutoModel;
import com.example.CarrinhoEcommerce.repositorie.EstoqueRepositorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {

    @Autowired
    EstoqueRepositorie estoqueRepositorie;

    @PostMapping("/estoque")
    public EstoqueModel adicionar(@RequestBody ProdutoDTO produtoDTO){
        ProdutoModel produto = new ProdutoModel();
        produto.setName(produtoDTO.name());
        produto.setPreco(produtoDTO.price());

        EstoqueModel estoqueModel = new EstoqueModel();
        estoqueModel.setProduto((Set<ProdutoModel>) produto);
        estoqueModel.setTipo("ENTRADA");
        estoqueModel.setQtd(5);

        estoqueRepositorie.save(estoqueModel);

        return estoqueModel;
    }
}
