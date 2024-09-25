package com.analise.gtaimport.service;

import com.analise.gtaimport.model.UnidadeEntity;
import com.analise.gtaimport.repository.UnidadeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnidadeService {

    @PersistenceContext
    private EntityManager entityManager;


    @Autowired
    private UnidadeRepository unidadeRepository;

    public UnidadeEntity salvar(UnidadeEntity unidade) {
        return unidadeRepository.save(unidade);
    }

    public List<UnidadeEntity> listar() {
        return unidadeRepository.findAll();
    }

    public UnidadeEntity findByName(String sigla, String name) {
        return unidadeRepository.findRelacional(entityManager, sigla);
    }
}
