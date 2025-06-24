package com.example.CarrinhoEcommerce.infra.security;

import com.example.CarrinhoEcommerce.repositorie.UserRepositorie;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class securityFilter extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;
    @Autowired
    UserRepositorie userRepositorie;
    // função ligada com o addFilterBefore do securityConfiguration
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);
        if (token != null) {
            var subject = tokenService.validateToken(token); // pega o login (username)
            UserDetails user = userRepositorie.findByLogin(subject);

            if (user != null) {
                var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                System.out.println("Usuário não encontrado para o subject extraído do token: " + subject);
            }
        }
        filterChain.doFilter(request,response); // chamando o filtro giagnte da classse do securiryConfiguration
        System.out.println("Authorization Header: " + request.getHeader("Authorization"));
    }

    // verifica se o token é valido
    private String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null) return null;
        return authHeader.replace("Bearer ", ""); // substitui o Bearer por uma string vazia para termos só a informação do login
    }
}
