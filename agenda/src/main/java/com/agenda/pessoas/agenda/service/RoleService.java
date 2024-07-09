package com.agenda.pessoas.agenda.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agenda.pessoas.agenda.dto.RoleDto;
import com.agenda.pessoas.agenda.repository.RoleRepository;
import com.agenda.pessoas.agenda.tables.Role;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RoleService {
    
    @Autowired
    private RoleRepository roleRepository;

    public List<RoleDto> getRoles() {
        return roleRepository.findAll().stream()
                .map(role -> new RoleDto(role.getId(), role.getNome()))
                .collect(Collectors.toList());
    }

    public RoleDto getRole(Long id) {
        Role roleDb = roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role não encontrada com ID: " + id));
        return new RoleDto(roleDb.getId(), roleDb.getNome());
    }

    public RoleDto saveRole(RoleDto roleDto) {
        Role role = new Role();
        role.setNome(roleDto.nome());
        Role roleDb = roleRepository.save(role);

        return new RoleDto(roleDb.getId(), roleDb.getNome());
    }

    public void deleteRole(RoleDto roleDto) {
        Role role = roleRepository.findById(roleDto.id())
                .orElseThrow(() -> new EntityNotFoundException("Role não encontrada com ID: " + roleDto.id()));
        roleRepository.delete(role);
    }
    
}
