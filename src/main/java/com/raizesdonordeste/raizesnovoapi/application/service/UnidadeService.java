package com.raizesdonordeste.raizesnovoapi.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.raizesdonordeste.raizesnovoapi.domain.Unidade;
import com.raizesdonordeste.raizesnovoapi.infrastructure.repository.UnidadeRepository;

@Service
public class UnidadeService {

    private final UnidadeRepository unidadeRepository;

    public UnidadeService(UnidadeRepository unidadeRepository) {
        this.unidadeRepository = unidadeRepository;
    }

    public Unidade salvar(Unidade unidade) {
        return unidadeRepository.save(unidade);
    }

    public List<Unidade> listarTodos() {
        return unidadeRepository.findAll();
    }

    public Unidade buscarPorId(Long id) {
        return unidadeRepository.findById(id).orElse(null);
    }

    public void deletarPorId(Long id) {
        unidadeRepository.deleteById(id);
    }
}
