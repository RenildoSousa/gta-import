package com.analise.gtaimport.repository;

import com.analise.gtaimport.model.StatusDemandaEntity;
import com.analise.gtaimport.model.StatusDemandaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusDemandaRepository extends JpaRepository<StatusDemandaEntity, Long> {
    
    default StatusDemandaEntity findRelacional(EntityManager entityManager, String nome) {
        try {
            // Corrigindo a criação da query com a entidade mapeada
            TypedQuery<StatusDemandaEntity> query = (TypedQuery<StatusDemandaEntity>) entityManager.createNativeQuery(
                    "SELECT * FROM StatusDemanda a WHERE a.nome LIKE :nome",
                    StatusDemandaEntity.class // Definindo o mapeamento para a entidade
            );

            query.setParameter("nome", "%" + nome + "%");

            return query.setMaxResults(1).getSingleResult(); // Retornando o primeiro resultado
        } catch (NoResultException e) {
            // Lidar com a situação em que nenhum resultado foi encontrado
            return null;
        }
    }
}
