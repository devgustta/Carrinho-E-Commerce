package com.example.CarrinhoEcommerce.controller;

import com.example.CarrinhoEcommerce.dto.ProdutoDTO;
import com.example.CarrinhoEcommerce.model.EstoqueModel;
import com.example.CarrinhoEcommerce.model.ProdutoModel;
import com.example.CarrinhoEcommerce.repositorie.EstoqueRepositorie;
import com.example.CarrinhoEcommerce.repositorie.ProdutoRepositorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @Autowired
    ProdutoRepositorie produtoRepositorie;

    @PostMapping("/estoque")
    public ResponseEntity<ProdutoModel> adicionar(@RequestBody ProdutoDTO produtoDTO){
        ProdutoModel produto = new ProdutoModel();
        produto.setName(produtoDTO.name());
        produto.setPreco(produtoDTO.price());
        //produtoRepositorie.save(produto);

        EstoqueModel estoqueModel = new EstoqueModel();
        estoqueModel.setProduto((Set<ProdutoModel>) produto);
        estoqueModel.setTipo("ENTRADA");
        estoqueModel.setQtd(5);

        return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepositorie.save(produto));/*estoqueRepositorie.save(estoqueModel)*/
    }
}
