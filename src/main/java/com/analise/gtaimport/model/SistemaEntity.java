package com.analise.gtaimport.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity(name = "Sistema")
public class SistemaEntity extends DefaultEntity {
    
    @Column(name = "nome", nullable = false)
    private String nome;
    
    @Column(name = "qtdeObjetivosEstrategicos")
    private String qtdeObjetivosEstrategicos;
    
    @Column(name = "desenvolvimentoParalelo")
    private String desenvolvimentoParalelo;
    
    public String getNome() {
        return this.nome;
    }
    
    public void setNome(final String nome) {
        this.nome = nome;
    }
    
    public String getQtdeObjetivosEstrategicos() {
        return this.qtdeObjetivosEstrategicos;
    }
    
    public void setQtdeObjetivosEstrategicos(final String qtdeObjetivosEstrategicos) {
        this.qtdeObjetivosEstrategicos = qtdeObjetivosEstrategicos;
    }
    
    public String getDesenvolvimentoParalelo() {
        return this.desenvolvimentoParalelo;
    }
    
    public void setDesenvolvimentoParalelo(final String desenvolvimentoParalelo) {
        this.desenvolvimentoParalelo = desenvolvimentoParalelo;
    }
}
