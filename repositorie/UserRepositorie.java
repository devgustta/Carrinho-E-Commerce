package com.example.CarrinhoEcommerce.repositorie;

import com.example.CarrinhoEcommerce.model.User;
import org.hibernate.boot.model.internal.JPAXMLOverriddenAnnotationReader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepositorie extends JpaRepository<User, UUID> {

    UserDetails findByLogin(String login); //

}
