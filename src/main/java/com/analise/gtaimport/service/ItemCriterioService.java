package com.analise.gtaimport.service;

import com.analise.gtaimport.model.ItemCriterioEntity;
import com.analise.gtaimport.repository.ItemCriterioRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemCriterioService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ItemCriterioRepository itemCriterioRepository;

    public ItemCriterioEntity salvar(ItemCriterioEntity servidor) {
        return itemCriterioRepository.save(servidor);
    }

    public List<ItemCriterioEntity> listar() {
        return itemCriterioRepository.findAll();
    }

    public ItemCriterioEntity findByName(String codigo,String nome) {
        return itemCriterioRepository.findRelacional(entityManager, codigo, nome);
    }
    public ItemCriterioEntity findByScore(String codigo,Double score) {
        return itemCriterioRepository.findRelacionalScore(entityManager, codigo, score);
    }
}
