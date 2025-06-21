package com.example.CarrinhoEcommerce.controller;


import com.example.CarrinhoEcommerce.model.CarrinhoModel;
import com.example.CarrinhoEcommerce.service.CarrinhoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/car")
public class CarrinhoController {

    CarrinhoService carrinhoService;

    public CarrinhoController(CarrinhoService carrinhoService){
        this.carrinhoService = carrinhoService;
    }

    @PostMapping("/car")
    public CarrinhoModel addNoCarrinho(HttpSession session){
        CarrinhoModel carrinho = (CarrinhoModel) session.getAttribute("carrinho");

        if (carrinho == null) {
            carrinho = new CarrinhoModel();
            session.setAttribute("carrinho", carrinho);
        }
        return carrinho;
    }

    /*public ResponseEntity<CarrinhoModel> remover(HttpSession session){
        CarrinhoModel carrinho = (CarrinhoModel) session.getAttribute("carrinho");

        if (carrinho == null) {
            carrinho = new CarrinhoModel();
            session.setAttribute("carrinho", carrinho);
        }

    }*/

}
