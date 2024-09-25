package com.analise.gtaimport.repository;

import com.analise.gtaimport.model.ItemCriterioEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCriterioRepository extends JpaRepository<ItemCriterioEntity, Long> {
    default ItemCriterioEntity findRelacional(EntityManager entityManager, String codigo, String nome) {
        try {
            TypedQuery<ItemCriterioEntity> query = (TypedQuery<ItemCriterioEntity>) entityManager.createNativeQuery(
                    "select ic.*" +
                            "from ItemCriterio ic" +
                            "         inner join Criterio c on ic.idCriterio = c.id " +
                            "where c.codigo like :codigo" +
                            "  and ic.nome LIKE :nome",
                    ItemCriterioEntity.class // Definindo o mapeamento para a entidade
            );

            query.setParameter("codigo", "%" + codigo + "%");
            query.setParameter("nome", "%" + nome + "%");

            return query.setMaxResults(1).getSingleResult(); // Retornando o primeiro resultado
        } catch (NoResultException e) {
            // Lidar com a situação em que nenhum resultado foi encontrado
            return null;
        }
    }

    default ItemCriterioEntity findRelacionalScore(EntityManager entityManager, String codigo, Double score) {
        try {
            TypedQuery<ItemCriterioEntity> query = (TypedQuery<ItemCriterioEntity>) entityManager.createNativeQuery(
                    "select ic.*" +
                            "from ItemCriterio ic" +
                            "         inner join Criterio c on ic.idCriterio = c.id " +
                            "where c.codigo like :codigo" +
                            "  and ic.score = :score",
                    ItemCriterioEntity.class // Definindo o mapeamento para a entidade
            );

            query.setParameter("codigo", "%" + codigo + "%");
            query.setParameter("score", score);

            return query.setMaxResults(1).getSingleResult(); // Retornando o primeiro resultado
        } catch (NoResultException e) {
            // Lidar com a situação em que nenhum resultado foi encontrado
            return null;
        }
    }
}
