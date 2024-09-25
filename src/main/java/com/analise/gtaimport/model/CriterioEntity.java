package com.analise.gtaimport.model;

import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;

@Entity(name = "Criterio")
public class CriterioEntity extends DefaultEntity {
    
    @Column(name = "codigo", nullable = false)
    private String codigo;
    
    @Column(name = "nome", nullable = false)
    private String nome;
    
    @Column(name = "descricao")
    private String descricao;
    
    @Column(name = "ativo", nullable = false)
    private boolean ativo;
    
//    @Enumerated(EnumType.STRING)
//    @Column(name = "tipo", nullable = false)
//    private TipoCriterioEnum tipoCriterio;
    
//    @OneToMany(mappedBy = "criterio")
//    private List<ItemCriterioEntity> itemCriterios;
    
    public CriterioEntity() {}
    
    public CriterioEntity(
            final Long id,
            final String codigo,
            final String nome,
            final String descricao,
            final boolean ativo,
            final TipoCriterioEnum tipoCriterio,
            final List<ItemCriterioEntity> itemCriterios) {
        
        this.id = id;
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.ativo = ativo;
//        this.tipoCriterio = tipoCriterio;
//        this.itemCriterios = itemCriterios;
    }
    
    public String getCodigo() {
        return this.codigo;
    }
    
    public void setCodigo(final String codigo) {
        this.codigo = codigo;
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
    
    public boolean isAtivo() {
        return this.ativo;
    }
    
    public void setAtivo(final boolean ativo) {
        this.ativo = ativo;
    }
    
//    public TipoCriterioEnum getTipoCriterio() {
//        return this.tipoCriterio;
//    }
//
//    public void setTipoCriterio(final TipoCriterioEnum tipoCriterio) {
//        this.tipoCriterio = tipoCriterio;
//    }
    
//    public List<ItemCriterioEntity> getItemCriterios() {
//        return this.itemCriterios;
//    }
//
//    public void setItemCriterios(final List<ItemCriterioEntity> itemCriterios) {
//        this.itemCriterios = itemCriterios;
//    }
    
}
