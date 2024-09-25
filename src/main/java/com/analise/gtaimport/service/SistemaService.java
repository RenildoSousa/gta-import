package com.analise.gtaimport.service;

import com.analise.gtaimport.model.SistemaEntity;
import com.analise.gtaimport.repository.SistemaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SistemaService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private SistemaRepository sistemaRepository;

    public SistemaEntity salvar(SistemaEntity servidor) {
        return sistemaRepository.save(servidor);
    }

    public List<SistemaEntity> listar() {
        return sistemaRepository.findAll();
    }

    public SistemaEntity findByName(String name) {
        return sistemaRepository.findRelacional(entityManager, name);
    }
}
