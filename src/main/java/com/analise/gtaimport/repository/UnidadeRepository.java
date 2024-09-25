package com.analise.gtaimport.repository;

import com.analise.gtaimport.model.UnidadeEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UnidadeRepository extends JpaRepository<UnidadeEntity, Long> {
    @Override
    List<UnidadeEntity> findAll();

    default UnidadeEntity findRelacional(EntityManager entityManager, String nome) {
        try {
            // Corrigindo a criação da query com a entidade mapeada
            TypedQuery<UnidadeEntity> query = (TypedQuery<UnidadeEntity>) entityManager.createNativeQuery(
                    "SELECT * FROM Unidade a WHERE a.sigla LIKE :sigla OR a.nome LIKE :nome",
                    UnidadeEntity.class // Definindo o mapeamento para a entidade
            );

            query.setParameter("sigla", "%" + nome + "%");
            query.setParameter("nome", "%" + nome + "%");

            return query.setMaxResults(1).getSingleResult(); // Retornando o primeiro resultado
        } catch (NoResultException e) {
            // Lidar com a situação em que nenhum resultado foi encontrado
            return null;
        }
    }
//
//    @Query(value = "SELECT * FROM Unidade WHERE sigla LIKE :silga OR nome LIKE :nome", nativeQuery = true)
//    UnidadeEntity findByNome(@Param("sigla") String sigla, @Param("nome") String nome);
}
