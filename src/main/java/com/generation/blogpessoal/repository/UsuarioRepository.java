package com.generation.blogpessoal.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.generation.blogpessoal.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Optional<Usuario> findByUsuario(String usuario); //Optional pois busca por um objeto especifico do usuario, geralmente o email

}