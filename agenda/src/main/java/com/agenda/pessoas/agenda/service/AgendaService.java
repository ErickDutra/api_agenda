package com.agenda.pessoas.agenda.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agenda.pessoas.agenda.dto.AgendaDto;
import com.agenda.pessoas.agenda.dto.UsuarioDto;
import com.agenda.pessoas.agenda.repository.AgendaRepository;
import com.agenda.pessoas.agenda.repository.UsuarioRepository;
import com.agenda.pessoas.agenda.tables.Agenda;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AgendaService {

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    public List<AgendaDto> getAgendas() {
        return agendaRepository.findAll().stream()
                .map(agenda -> new AgendaDto(agenda.getId(), agenda.getDisponivel(), agenda.getHorario(), agenda.getUsuario(), agenda.getData_disponivel()))
                .collect(Collectors.toList());
    }

    public AgendaDto getAgenda(Long id) {
        Agenda agendaDb = agendaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agenda não encontrada com ID: " + id));
        return new AgendaDto(agendaDb.getId(), agendaDb.getDisponivel(),agendaDb.getHorario(), agendaDb.getUsuario(), agendaDb.getData_disponivel());
    }

    public AgendaDto saveAgenda(AgendaDto agendaDto) {
        Agenda agenda = new Agenda();

        agenda.setId(agendaDto.id());
        agenda.setDisponivel(true);
        agenda.setHorario(agendaDto.horario());
        agenda.setUsuario(null);
        Agenda agendaDb = agendaRepository.save(agenda);

        return new AgendaDto(agendaDb.getId(), agendaDb.getDisponivel(),agendaDb.getHorario(), agendaDb.getUsuario(), agendaDb.getData_disponivel());
    }

    public AgendaDto updateAgenda( AgendaDto agendaDto, UsuarioDto usuarioDto) {
        Agenda agenda = agendaRepository.findById(agendaDto.id())
                .orElseThrow(() -> new EntityNotFoundException("Agenda não encontrada com ID: " +agendaDto.id()));

        agenda.setDisponivel(agendaDto.disponivel());
        agenda.setHorario(agendaDto.horario());
        agenda.setUsuario(usuarioRepository.findById(usuarioDto.id())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + usuarioDto.id())));
        Agenda agendaDb = agendaRepository.save(agenda);

        return new AgendaDto(agendaDb.getId(), agendaDb.getDisponivel(),agendaDb.getHorario(), agendaDb.getUsuario(), agendaDb.getData_disponivel());
    }

    public void deleteAgenda(Long id) {
        Agenda agenda = agendaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agenda não encontrada com ID: " + id));
        agendaRepository.delete(agenda);
    }
   
    
}
