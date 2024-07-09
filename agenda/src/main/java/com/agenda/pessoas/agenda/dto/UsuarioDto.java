package com.agenda.pessoas.agenda.dto;



import java.util.Date;

import com.agenda.pessoas.agenda.tables.Role;

public record UsuarioDto(Long id, String nome, String email, String senha, String cpf, Date dataCadastro, Role role) {
}
