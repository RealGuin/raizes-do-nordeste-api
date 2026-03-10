package com.raizesdonordeste.raizesnovoapi.application.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.raizesdonordeste.raizesnovoapi.api.dto.PaginacaoResponse;
import com.raizesdonordeste.raizesnovoapi.api.dto.UsuarioRequest;
import com.raizesdonordeste.raizesnovoapi.api.dto.UsuarioResponse;
import com.raizesdonordeste.raizesnovoapi.domain.CanalPedido;
import com.raizesdonordeste.raizesnovoapi.domain.Role;
import com.raizesdonordeste.raizesnovoapi.domain.Usuario;
import com.raizesdonordeste.raizesnovoapi.domain.exception.ConflictException;
import com.raizesdonordeste.raizesnovoapi.domain.exception.ValidacaoException;
import com.raizesdonordeste.raizesnovoapi.infrastructure.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private static final Logger log = LoggerFactory.getLogger(UsuarioService.class);
    
    public UsuarioService(UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder) {
		this.usuarioRepository = usuarioRepository;
		this.passwordEncoder = passwordEncoder;
    }
    

    public UsuarioResponse salvar(UsuarioRequest request) {
        
    	if (request.getRole() == Role.CLIENTE
    	        && (request.getCanalPedido() == CanalPedido.APP 
    	        || request.getCanalPedido() == CanalPedido.WEB)
    	        && !request.isConsentimentoLgpd()) {
    	    throw new ValidacaoException(
    	        "Para cadastro de cliente via APP ou WEB, o consentimento LGPD é obrigatório"
    	    );
    	}
    	
    	if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new ConflictException(
                    "Já existe um usuário cadastrado com este email"                
            );
    	}
    	
    	Usuario usuario = new Usuario();
        usuario.setNome(request.getNome());
        usuario.setEmail(request.getEmail());
        usuario.setSenhaHash(passwordEncoder.encode(request.getSenhaHash()));
        usuario.setRole(request.getRole());
        usuario.setConsentimentoLgpd(request.isConsentimentoLgpd());
        
        

        if (request.isConsentimentoLgpd()) {
            usuario.setDataConsentimento(LocalDateTime.now());
        }

        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        
        log.info("Usuario criado - id={}, email={}, role={}",
                usuarioSalvo.getId(),
                usuarioSalvo.getEmail(),
                usuarioSalvo.getRole());

        UsuarioResponse response = new UsuarioResponse();
        response.setId(usuarioSalvo.getId());
        response.setNome(usuarioSalvo.getNome());
        response.setEmail(usuarioSalvo.getEmail());
        response.setRole(usuarioSalvo.getRole());
        response.setConsentimentoLgpd(usuarioSalvo.isConsentimentoLgpd());
        response.setDataConsentimento(usuarioSalvo.getDataConsentimento());

        return response;
    }

    public PaginacaoResponse<UsuarioResponse> listar(Pageable paginacao) {

        Page<Usuario> paginaUsuarios = usuarioRepository.findAll(paginacao);

        List<UsuarioResponse> itens = paginaUsuarios.getContent().stream()
                .map(usuario -> {
                    UsuarioResponse response = new UsuarioResponse();
                    response.setId(usuario.getId());
                    response.setNome(usuario.getNome());
                    response.setEmail(usuario.getEmail());
                    response.setRole(usuario.getRole());
                    response.setConsentimentoLgpd(usuario.isConsentimentoLgpd());
                    response.setDataConsentimento(usuario.getDataConsentimento());
                    return response;
                })
                .toList();

        PaginacaoResponse<UsuarioResponse> response = new PaginacaoResponse<>();
        response.setItens(itens);
        response.setPagina(paginaUsuarios.getNumber());
        response.setTotalPaginas(paginaUsuarios.getTotalPages());
        response.setTotalItens(paginaUsuarios.getTotalElements());

        return response;
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
        response.setRole(usuario.getRole());
        response.setConsentimentoLgpd(usuario.isConsentimentoLgpd());
        response.setDataConsentimento(usuario.getDataConsentimento());

        return response;
    }

    public void deletarPorId(Long id) {
        usuarioRepository.deleteById(id);
    }
}