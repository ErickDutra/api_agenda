package com.agenda.pessoas.agenda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.agenda.pessoas.agenda.service.UsuarioService;

@RestController
@CrossOrigin("*")
@RequestMapping("/agenda")
public class AgendaController {
    
    @Autowired
    private AgendaService agendaService;

    @Autowired
    private UsuarioService usuarioService;

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

    @PutMapping("/{agendaId}/agendar/{usuarioId}")
    public ResponseEntity<AgendaDto> updateAgenda(@PathVariable Long agendaId, @PathVariable Long usuarioId) {

        UsuarioDto usuario = usuarioService.getUsuario(usuarioId);
        if (usuario == null) {
            return ResponseEntity.badRequest().build();
        }

        AgendaDto agenda = agendaService.getAgenda(agendaId);
        if (agenda == null) {
            return ResponseEntity.badRequest().build();
        }

        agendaService.updateAgenda(agenda, usuario);
        return ResponseEntity.ok(agenda);
    }

}
