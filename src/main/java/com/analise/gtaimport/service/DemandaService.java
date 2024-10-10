package com.analise.gtaimport.service;

import com.analise.gtaimport.model.DemandaEntity;
import com.analise.gtaimport.repository.DemandaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemandaService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private DemandaRepository demandaRepository;

    public DemandaEntity salvar(DemandaEntity servidor) {
        return demandaRepository.save(servidor);
    }

    public List<DemandaEntity> listar() {
        return demandaRepository.findAll();
    }

    public DemandaEntity findByName(String name) {
        return demandaRepository.findRelacional(entityManager, name);
    }

    public void deleteAll(){
        demandaRepository.deleteAll();
    }
}
