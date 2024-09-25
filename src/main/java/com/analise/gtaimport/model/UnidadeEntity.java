package com.analise.gtaimport.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity(name = "Unidade")
public class UnidadeEntity extends DefaultEntity {
    
    @Column(name = "sigla", nullable = false)
    private String sigla;
    
    @Column(name = "nome")
    private String nome;
    
    public String getSigla() {
        return this.sigla;
    }
    
    public void setSigla(final String sigla) {
        this.sigla = sigla;
    }
    
    public String getNome() {
        return this.nome;
    }
    
    public void setNome(final String nome) {
        this.nome = nome;
    }
}
