package com.agenda.pessoas.agenda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agenda.pessoas.agenda.dto.AgendaDto;
import com.agenda.pessoas.agenda.dto.UsuarioDto;
import com.agenda.pessoas.agenda.service.AgendaService;

@RestController
@CrossOrigin("*")
@RequestMapping("/agenda")
public class AgendaController {
    
    @Autowired
    private AgendaService agendaService;

    @GetMapping
    public List<AgendaDto> getAgendas() {
        return agendaService.getAgendas();
    }

    @GetMapping("/{id}")
    public AgendaDto getAgenda(@PathVariable Long id) {
        return agendaService.getAgenda(id);
    }

    @PostMapping
    public AgendaDto saveAgenda(@RequestBody AgendaDto agendaDto) {
        return agendaService.saveAgenda(agendaDto);
    }

    @DeleteMapping("/{id}")
    public void deleteAgenda(@PathVariable Long id) {
        agendaService.deleteAgenda(id);
    }

    @PutMapping("/{id}")
    public AgendaDto updateAgenda(@PathVariable Long id, @RequestBody AgendaDto agendaDto, @RequestBody UsuarioDto usuarioDto) {
        return agendaService.updateAgenda(id, agendaDto, usuarioDto);
    }

}
