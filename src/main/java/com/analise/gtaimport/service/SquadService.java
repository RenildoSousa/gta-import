package com.analise.gtaimport.service;

import com.analise.gtaimport.model.SquadEntity;
import com.analise.gtaimport.repository.SquadRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SquadService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private SquadRepository squadRepository;

    public SquadEntity salvar(SquadEntity servidor) {
        return squadRepository.save(servidor);
    }

    public List<SquadEntity> listar() {
        return squadRepository.findAll();
    }

    public SquadEntity findByName(String name) {
        return squadRepository.findRelacional(entityManager, name);
    }
}
