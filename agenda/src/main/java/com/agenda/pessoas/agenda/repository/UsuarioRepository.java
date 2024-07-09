package com.agenda.pessoas.agenda.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agenda.pessoas.agenda.tables.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{    
    Optional<Usuario> findByEmailAndSenha(String email, String senha);
}
