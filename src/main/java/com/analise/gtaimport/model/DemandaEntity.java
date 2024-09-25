package com.analise.gtaimport.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity(name = "Demanda")
public class DemandaEntity extends DefaultEntity {
    
    @Column(name = "dataSolicitacao", nullable = true)
    private LocalDateTime dataSolicitacao;
    
    @Column(name = "documentoSeiSolicitacao")
    private String documentoSeiSolicitacao;
    
    @Column(name = "descricao")
    private String descricao;
    
    @Column(name = "observacao")
    private String observacao;
    
    @Column(name = "previsaoEntrega")
    private LocalDate previsaoEntrega;
    
    @Column(name = "dataEntrega")
    private LocalDateTime dataEntrega;
    
    @Column(name = "documentoSeiEntrega")
    private String documentoSeiEntrega;
    
    @ManyToOne
    @JoinColumn(name = "idUnidade", nullable = false)
    private UnidadeEntity unidade;
    
    @ManyToOne
    @JoinColumn(name = "idServidor", nullable = false)
    private ServidorEntity servidor;
    
    @ManyToOne
    @JoinColumn(name = "idSquad", nullable = false)
    private SquadEntity squad;
    
    @ManyToOne
    @JoinColumn(name = "idSistema", nullable = false)
    private SistemaEntity sistema;
    
    @ManyToOne
    @JoinColumn(name = "idStatusDemanda", nullable = false)
    private StatusDemandaEntity statusDemanda;
    
    @ManyToMany
    @JoinTable(
            name = "Classificacao",
            joinColumns = @JoinColumn(name = "idDemanda"),
            inverseJoinColumns = @JoinColumn(name = "idItemCriterio")
    )
    private List<ItemCriterioEntity> listaClassificacao;
    
    public DemandaEntity() {}
    
    public DemandaEntity(
            final LocalDateTime dataSolicitacao,
            final String documentoSeiSolicitacao,
            final String descricao,
            final String observacao,
            final LocalDate previsaoEntrega,
            final LocalDateTime dataEntrega,
            final String documentoSeiEntrega,
            final UnidadeEntity unidade,
            final ServidorEntity servidor,
            final SquadEntity squad,
            final SistemaEntity sistema,
            final StatusDemandaEntity statusDemanda,
            final List<ItemCriterioEntity> listaClassificacao) {
        
        this.dataSolicitacao = dataSolicitacao;
        this.documentoSeiSolicitacao = documentoSeiSolicitacao;
        this.descricao = descricao;
        this.observacao = observacao;
        this.previsaoEntrega = previsaoEntrega;
        this.dataEntrega = dataEntrega;
        this.documentoSeiEntrega = documentoSeiEntrega;
        this.unidade = unidade;
        this.servidor = servidor;
        this.squad = squad;
        this.sistema = sistema;
        this.statusDemanda = statusDemanda;
        this.listaClassificacao = listaClassificacao;
    }
    
    public LocalDateTime getDataSolicitacao() {
        return this.dataSolicitacao;
    }
    
    public void setDataSolicitacao(final LocalDateTime dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }
    
    public String getDocumentoSeiSolicitacao() {
        return this.documentoSeiSolicitacao;
    }
    
    public void setDocumentoSeiSolicitacao(final String documentoSeiSolicitacao) {
        this.documentoSeiSolicitacao = documentoSeiSolicitacao;
    }
    
    public String getDescricao() {
        return this.descricao;
    }
    
    public void setDescricao(final String descricao) {
        this.descricao = descricao;
    }
    
    public String getObservacao() {
        return this.observacao;
    }
    
    public void setObservacao(final String observacao) {
        this.observacao = observacao;
    }
    
    public LocalDate getPrevisaoEntrega() {
        return this.previsaoEntrega;
    }
    
    public void setPrevisaoEntrega(final LocalDate previsaoEntrega) {
        this.previsaoEntrega = previsaoEntrega;
    }
    
    public LocalDateTime getDataEntrega() {
        return this.dataEntrega;
    }
    
    public void setDataEntrega(final LocalDateTime dataEntrega) {
        this.dataEntrega = dataEntrega;
    }
    
    public String getDocumentoSeiEntrega() {
        return this.documentoSeiEntrega;
    }
    
    public void setDocumentoSeiEntrega(final String documentoSeiEntrega) {
        this.documentoSeiEntrega = documentoSeiEntrega;
    }
    
    public UnidadeEntity getUnidade() {
        return this.unidade;
    }
    
    public void setUnidade(final UnidadeEntity unidade) {
        this.unidade = unidade;
    }
    
    public ServidorEntity getServidor() {
        return this.servidor;
    }
    
    public void setServidor(final ServidorEntity servidor) {
        this.servidor = servidor;
    }
    
    public SquadEntity getSquad() {
        return this.squad;
    }
    
    public void setSquad(final SquadEntity squad) {
        this.squad = squad;
    }
    
    public SistemaEntity getSistema() {
        return this.sistema;
    }
    
    public void setSistema(final SistemaEntity sistema) {
        this.sistema = sistema;
    }
    
    public StatusDemandaEntity getStatusDemanda() {
        return this.statusDemanda;
    }
    
    public void setStatusDemanda(final StatusDemandaEntity statusDemanda) {
        this.statusDemanda = statusDemanda;
    }
    
    public List<ItemCriterioEntity> getListaClassificacao() {
        return this.listaClassificacao;
    }
    
    public void setListaClassificacao(final List<ItemCriterioEntity> listaClassificacao) {
        this.listaClassificacao = listaClassificacao;
    }
}
