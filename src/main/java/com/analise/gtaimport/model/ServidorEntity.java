package com.analise.gtaimport.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity(name = "Servidor")
public class ServidorEntity extends DefaultEntity {
    
    @Column(name = "nome", nullable = false)
    private String nome;
    
    @Column(name = "cpf")
    private String cpf;
    
    public String getNome() {
        return this.nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getCpf() {
        return this.cpf;
    }
    
    public void setCpf(final String cpf) {
        this.cpf = cpf;
    }
}
