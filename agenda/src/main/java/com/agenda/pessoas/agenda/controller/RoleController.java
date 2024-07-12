package com.agenda.pessoas.agenda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agenda.pessoas.agenda.dto.RoleDto;
import com.agenda.pessoas.agenda.service.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public ResponseEntity<List<RoleDto>> getRoles() {
        List<RoleDto> roles = roleService.getRoles();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDto> getRole(@PathVariable Long id) {
        RoleDto role = roleService.getRole(id);
        return ResponseEntity.ok(role);
    }

    @PostMapping
    public ResponseEntity<RoleDto> saveRole(@RequestBody RoleDto roleDto) {
        RoleDto savedRole = roleService.saveRole(roleDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRole);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteRole(@RequestBody RoleDto roleDto) {
        roleService.deleteRole(roleDto);
        return ResponseEntity.noContent().build();
    }
}