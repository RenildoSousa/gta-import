package com.analise.gtaimport.service;

import com.analise.gtaimport.model.StatusDemandaEntity;
import com.analise.gtaimport.repository.StatusDemandaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusDemandaService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private StatusDemandaRepository statusDemandaRepository;

    public StatusDemandaEntity salvar(StatusDemandaEntity servidor) {
        return statusDemandaRepository.save(servidor);
    }

    public List<StatusDemandaEntity> listar() {
        return statusDemandaRepository.findAll();
    }

    public StatusDemandaEntity findByName(String name) {
        return statusDemandaRepository.findRelacional(entityManager, name);
    }
}
