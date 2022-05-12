package com.example.spring.repository;

import com.example.spring.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    //Query nativa
    @Query(value = "SELECT * FROM usuario WHERE nome = :nome", nativeQuery = true)
    Usuario findByName(String nome);

    //Query jpql
    @Query(value = "SELECT u FROM Usuario u WHERE u.nome= :nome")
    Usuario findByNameJPQL(String nome);

//    @Query(value = "SELECT * FROM usuario WHERE email = :email", nativeQuery = true )
//    Usuario findByEmail(String email);
}
