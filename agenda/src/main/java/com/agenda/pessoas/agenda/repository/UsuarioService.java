package com.agenda.pessoas.agenda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agenda.pessoas.agenda.tables.Usuarios;

public interface UsuarioService extends JpaRepository<Usuarios, Long>{    

}
