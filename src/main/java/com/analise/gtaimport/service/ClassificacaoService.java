package com.analise.gtaimport.service;

import com.analise.gtaimport.model.ItemCriterioEntity;
import com.analise.gtaimport.repository.ClassificacaoRepository;
import com.analise.gtaimport.repository.ItemCriterioRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassificacaoService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ClassificacaoRepository classificacaoRepository;

    public void deleteAll(){
        classificacaoRepository.deleteAll();
    }
}
