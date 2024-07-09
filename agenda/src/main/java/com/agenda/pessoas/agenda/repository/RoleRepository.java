package com.agenda.pessoas.agenda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agenda.pessoas.agenda.tables.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
    
}
