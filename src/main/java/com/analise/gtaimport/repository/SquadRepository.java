package com.analise.gtaimport.repository;

import com.analise.gtaimport.model.SquadEntity;
import com.analise.gtaimport.model.SquadEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SquadRepository extends JpaRepository<SquadEntity, Long> {

    default SquadEntity findRelacional(EntityManager entityManager, String nome) {
        try {
            // Corrigindo a criação da query com a entidade mapeada
            TypedQuery<SquadEntity> query = (TypedQuery<SquadEntity>) entityManager.createNativeQuery(
                    "SELECT * FROM Squad a WHERE a.nome LIKE :nome",
                    SquadEntity.class // Definindo o mapeamento para a entidade
            );

            query.setParameter("nome", "%" + nome + "%");

            return query.setMaxResults(1).getSingleResult(); // Retornando o primeiro resultado
        } catch (NoResultException e) {
            // Lidar com a situação em que nenhum resultado foi encontrado
            return null;
        }
    }
}
