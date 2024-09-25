package com.analise.gtaimport.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "ItemCriterio")
public class ItemCriterioEntity extends DefaultEntity {
    
    @Column(name = "nome")
    private String nome;
    
    @Column(name = "descricao", nullable = false)
    private String descricao;
    
    @Column(name = "score", nullable = false)
    private Float score;
    
    @Column(name = "ativo", nullable = false)
    private boolean ativo;
    
    @ManyToOne
    @JoinColumn(name = "idCriterio", nullable = false)
    private CriterioEntity criterio;
    
    public ItemCriterioEntity() {}
    
    public ItemCriterioEntity(
            final Long id,
            final String nome,
            final String descricao,
            final Float score,
            final boolean ativo,
            final CriterioEntity criterio) {
        
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.score = score;
        this.ativo = ativo;
        this.criterio = criterio;
    }
    
    public String getNome() {
        return this.nome;
    }
    
    public void setNome(final String nome) {
        this.nome = nome;
    }
    
    public String getDescricao() {
        return this.descricao;
    }
    
    public void setDescricao(final String descricao) {
        this.descricao = descricao;
    }
    
    public Float getScore() {
        return this.score;
    }
    
    public void setScore(final Float score) {
        this.score = score;
    }
    
    public boolean isAtivo() {
        return this.ativo;
    }
    
    public void setAtivo(final boolean ativo) {
        this.ativo = ativo;
    }
    
    public CriterioEntity getCriterio() {
        return this.criterio;
    }
    
    public void setCriterio(final CriterioEntity criterio) {
        this.criterio = criterio;
    }
}
