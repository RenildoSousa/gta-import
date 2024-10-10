package com.analise.gtaimport.repository;

import com.analise.gtaimport.model.Classificacao;
import com.analise.gtaimport.model.CriterioEntity;
import com.analise.gtaimport.model.DemandaEntity;
import com.analise.gtaimport.model.ItemCriterioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassificacaoRepository extends JpaRepository<DemandaEntity, ItemCriterioEntity> {


}
