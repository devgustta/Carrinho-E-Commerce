package com.example.CarrinhoEcommerce.controller;

import com.example.CarrinhoEcommerce.dto.AuthenticationDTO;
import com.example.CarrinhoEcommerce.dto.LoginResponseDTO;
import com.example.CarrinhoEcommerce.dto.RegisterDTO;
import com.example.CarrinhoEcommerce.infra.security.TokenService;
import com.example.CarrinhoEcommerce.model.User;
import com.example.CarrinhoEcommerce.repositorie.UserRepositorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepositorie repositorie;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationDTO data){
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
            var auth = this.authenticationManager.authenticate(usernamePassword);

            var token = tokenService.generateToken((User) auth.getPrincipal());

            return ResponseEntity.ok(new LoginResponseDTO(token));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(403).body("Erro no login: " + e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterDTO data){
        if(this.repositorie.findByLogin(data.login()) != null){
            return ResponseEntity.badRequest().build();
        }

        String incriptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User user = new User(data.login(), incriptedPassword, data.role());

        this.repositorie.save(user);

        return ResponseEntity.ok().build();
    }

}
