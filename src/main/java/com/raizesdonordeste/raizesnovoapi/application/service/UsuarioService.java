package com.raizesdonordeste.raizesnovoapi.application.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.raizesdonordeste.raizesnovoapi.api.dto.UsuarioRequest;
import com.raizesdonordeste.raizesnovoapi.api.dto.UsuarioResponse;
import com.raizesdonordeste.raizesnovoapi.domain.Role;
import com.raizesdonordeste.raizesnovoapi.domain.Usuario;
import com.raizesdonordeste.raizesnovoapi.infrastructure.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioResponse salvar(UsuarioRequest request) {
        Usuario usuario = new Usuario();
        usuario.setNome(request.getNome());
        usuario.setEmail(request.getEmail());
        usuario.setSenhaHash(request.getSenhaHash());
        usuario.setRole(Role.valueOf(request.getRole()));
        usuario.setConsentimentoLgpd(request.isConsentimentoLgpd());

        if (request.isConsentimentoLgpd()) {
            usuario.setDataConsentimento(LocalDateTime.now());
        }

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        UsuarioResponse response = new UsuarioResponse();
        response.setId(usuarioSalvo.getId());
        response.setNome(usuarioSalvo.getNome());
        response.setEmail(usuarioSalvo.getEmail());
        response.setRole(usuarioSalvo.getRole().name());
        response.setConsentimentoLgpd(usuarioSalvo.isConsentimentoLgpd());
        response.setDataConsentimento(usuarioSalvo.getDataConsentimento());

        return response;
    }

    public List<UsuarioResponse> listarTodos() {
        List<Usuario> usuarios = usuarioRepository.findAll();

        return usuarios.stream().map(usuario -> {
            UsuarioResponse response = new UsuarioResponse();
            response.setId(usuario.getId());
            response.setNome(usuario.getNome());
            response.setEmail(usuario.getEmail());
            response.setRole(usuario.getRole().name());
            response.setConsentimentoLgpd(usuario.isConsentimentoLgpd());
            response.setDataConsentimento(usuario.getDataConsentimento());
            return response;
        }).toList();
    }

    public UsuarioResponse buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);

        if (usuario == null) {
            return null;
        }

        UsuarioResponse response = new UsuarioResponse();
        response.setId(usuario.getId());
        response.setNome(usuario.getNome());
        response.setEmail(usuario.getEmail());
        response.setRole(usuario.getRole().name());
        response.setConsentimentoLgpd(usuario.isConsentimentoLgpd());
        response.setDataConsentimento(usuario.getDataConsentimento());

        return response;
    }

    public void deletarPorId(Long id) {
        usuarioRepository.deleteById(id);
    }
}