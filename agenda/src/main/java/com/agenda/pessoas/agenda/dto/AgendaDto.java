package com.agenda.pessoas.agenda.dto;

import java.util.Date;
import com.agenda.pessoas.agenda.tables.Usuario;

public record AgendaDto(Long id, Boolean disponivel,Date horario, Usuario Usuario, Date data_disponivel) {
    
}
