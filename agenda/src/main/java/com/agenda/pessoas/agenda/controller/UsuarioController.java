package com.agenda.pessoas.agenda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agenda.pessoas.agenda.dto.RoleDto;
import com.agenda.pessoas.agenda.dto.UsuarioDto;
import com.agenda.pessoas.agenda.service.UsuarioService;

@RestController
@CrossOrigin("*")
@RequestMapping("/usuario")
public class UsuarioController {
    
     @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/{id}")
    public UsuarioDto getUsuario(@PathVariable Long id) {
        return usuarioService.getUsuario(id);
    }

    @PostMapping
    public ResponseEntity<UsuarioDto> createUsuario(@RequestBody UsuarioDto usuarioDto, @RequestHeader("roleId") Long roleId) {
        UsuarioDto savedUsuario = usuarioService.saveUsuario(usuarioDto, roleId);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUsuario);
    }

    @PutMapping("/{id}")
    public UsuarioDto updateUsuario(@PathVariable Long id, @RequestBody UsuarioDto usuario, @RequestBody RoleDto role) {
        return usuarioService.updateUsuario(id, usuario, role);
    }

    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable UsuarioDto usuario) {
        usuarioService.deleteUsuario(usuario);
    }

   @GetMapping("/login")
    public UsuarioDto findByEmailAndSenha(@RequestHeader("email") String email, @RequestHeader("senha") String senha) {
        return usuarioService.login(email, senha);
    }
}
