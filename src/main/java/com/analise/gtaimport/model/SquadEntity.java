package com.analise.gtaimport.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity(name = "Squad")
public class SquadEntity extends DefaultEntity {
    
    @Column(name = "nome", nullable = false)
    private String nome;
    
    public String getNome() {
        return this.nome;
    }
    
    public void setNome(final String nome) {
        this.nome = nome;
    }
    
}
