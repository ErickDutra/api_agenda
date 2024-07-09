package com.agenda.pessoas.agenda.service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agenda.pessoas.agenda.dto.RoleDto;
import com.agenda.pessoas.agenda.dto.UsuarioDto;
import com.agenda.pessoas.agenda.repository.RoleRepository;
import com.agenda.pessoas.agenda.repository.UsuarioRepository;
import com.agenda.pessoas.agenda.tables.Usuario;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UsuarioService {
    
    @Autowired
    private  UsuarioRepository usuarioRepository;

    private RoleRepository roleRepository;

    public List<UsuarioDto> getUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(usuario -> new UsuarioDto(usuario.getId(), usuario.getNome(), usuario.getCpf(), usuario.getEmail(), usuario.getSenha(), usuario.getDateRegister(), usuario.getRole()))
                .toList();
    }

    public UsuarioDto getUsuario(Long id) {
        Usuario usuarioDb = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + id));
        return new UsuarioDto(usuarioDb.getId(), usuarioDb.getNome(), usuarioDb.getCpf(), usuarioDb.getEmail(), usuarioDb.getSenha(), usuarioDb.getDateRegister(), usuarioDb.getRole());
    }

    public UsuarioDto saveUsuario(UsuarioDto usuarioDto, RoleDto roleDto) {

        Usuario usuario = new Usuario();

        usuario.setNome(usuarioDto.nome());
        usuario.setCpf(usuarioDto.cpf());
        usuario.setEmail(usuarioDto.email());
        usuario.setSenha(usuarioDto.senha());
        usuario.setDateRegister(Date.from(Instant.now()));
        usuario.setRole(roleRepository.findById(roleDto.id())
                        .orElseThrow(() -> new EntityNotFoundException("Role não encontrada com ID: " + roleDto.id())));
        Usuario usuarioDb = usuarioRepository.save(usuario);

        return new UsuarioDto(usuarioDb.getId(), usuarioDb.getNome(), usuarioDb.getCpf(), usuarioDb.getEmail(), usuarioDb.getSenha(), usuarioDb.getDateRegister(), usuarioDb.getRole());
    }

    public void deleteUsuario(UsuarioDto usuarioDto) {
        Usuario usuario = usuarioRepository.findById(usuarioDto.id())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + usuarioDto.id()));
        usuarioRepository.delete(usuario);
    }

    public UsuarioDto updateUsuario(Long id, UsuarioDto usuarioDto, RoleDto roleDto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + id));

        usuario.setNome(usuarioDto.nome());
        usuario.setCpf(usuarioDto.cpf());
        usuario.setEmail(usuarioDto.email());
        usuario.setSenha(usuarioDto.senha());
        usuario.setDateRegister(Date.from(Instant.now()));
        usuario.setRole(roleRepository.findById(roleDto.id())
                        .orElseThrow(() -> new EntityNotFoundException("Role não encontrada com ID: " + roleDto.id())));
        usuarioRepository.save(usuario);

        return new UsuarioDto(
                usuario.getId(), 
                usuario.getNome(), 
                usuario.getCpf(), 
                usuario.getEmail(),
                usuario.getSenha(),
                usuario.getDateRegister(),
                usuario.getRole()
        );
    }

    public UsuarioDto login(String email, String senha) {
        Usuario usuario = usuarioRepository.findByEmailAndSenha(email, senha)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com email: " + email));
        return new UsuarioDto(
                usuario.getId(), 
                usuario.getNome(), 
                usuario.getCpf(), 
                usuario.getEmail(), 
                usuario.getSenha(), 
                usuario.getDateRegister(),
                usuario.getRole()
        );
    }
}
