package com.analise.gtaimport.service;

import com.analise.gtaimport.model.ServidorEntity;
import com.analise.gtaimport.repository.ServidorRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServidorService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ServidorRepository servidorRepository;

    public ServidorEntity salvar(ServidorEntity servidor) {
        return servidorRepository.save(servidor);
    }

    public List<ServidorEntity> listar() {
        return servidorRepository.findAll();
    }

    public ServidorEntity findByName(String name) {
        return servidorRepository.findRelacional(entityManager, name);
    }
}
