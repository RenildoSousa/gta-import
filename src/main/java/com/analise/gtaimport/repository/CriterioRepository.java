package com.analise.gtaimport.repository;

import com.analise.gtaimport.model.CriterioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CriterioRepository extends JpaRepository<CriterioEntity, Long> {
}
