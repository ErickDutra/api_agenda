package com.agenda.pessoas.agenda.dto;

import java.util.Date;
import com.agenda.pessoas.agenda.tables.Usuarios;

public record AgendaDto(Long id, Boolean disponivel,Date horario, Usuarios id_usuario, Date data_disponivel) {
    
}
