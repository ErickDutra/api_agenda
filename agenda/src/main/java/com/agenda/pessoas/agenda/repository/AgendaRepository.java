package com.agenda.pessoas.agenda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agenda.pessoas.agenda.tables.Agenda;

public interface AgendaRepository extends JpaRepository<Agenda, Long>{
    
}
