package com.raizesdonordeste.raizesnovoapi.application.service;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.raizesdonordeste.raizesnovoapi.api.dto.LoginRequest;
import com.raizesdonordeste.raizesnovoapi.api.dto.LoginResponse;
import com.raizesdonordeste.raizesnovoapi.domain.Usuario;
import com.raizesdonordeste.raizesnovoapi.domain.exception.UnauthorizedException;
import com.raizesdonordeste.raizesnovoapi.infrastructure.repository.UsuarioRepository;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private static final Logger log = LoggerFactory.getLogger(AuthService.class);

    public AuthService(UsuarioRepository usuarioRepository,
			            PasswordEncoder passwordEncoder,
			            JwtService jwtService) {
		this.usuarioRepository = usuarioRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
    }

    public LoginResponse login(LoginRequest request) {

    	Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
    	        .orElseThrow(() -> new UnauthorizedException("Email ou senha inválidos"));

    	boolean senhaCorreta = passwordEncoder.matches(request.getSenha(), usuario.getSenhaHash());

    	if (!senhaCorreta) {
    	    throw new UnauthorizedException("Email ou senha inválidos");
    	}
        
        log.info("Usuario autenticado - id={}, role={}",
                usuario.getId(),
                usuario.getRole());

        LoginResponse response = new LoginResponse();
        response.setId(usuario.getId());
        response.setToken(jwtService.gerarToken(usuario));
        response.setTipo("Bearer");
        response.setRole(usuario.getRole().name());
        

        return response;
    }
}
