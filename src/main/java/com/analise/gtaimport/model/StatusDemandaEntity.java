package com.analise.gtaimport.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity(name = "StatusDemanda")
public class StatusDemandaEntity extends DefaultEntity {
    
    @Column(name = "nome", nullable = false)
    private String nome;
    
    @Column(name = "descricao")
    private String descricao;
    
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
}
