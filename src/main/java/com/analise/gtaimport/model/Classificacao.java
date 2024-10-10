package com.analise.gtaimport.model;

import jakarta.persistence.*;

@Embeddable
public class Classificacao {

    @ManyToOne
    private DemandaEntity demanda;
    @ManyToOne
    private ItemCriterioEntity itemCriterioEntity;


    public DemandaEntity getDemanda() {
        return demanda;
    }

    public void setDemanda(DemandaEntity demanda) {
        this.demanda = demanda;
    }

    public ItemCriterioEntity getItemCriterioEntity() {
        return itemCriterioEntity;
    }

    public void setItemCriterioEntity(ItemCriterioEntity itemCriterioEntity) {
        this.itemCriterioEntity = itemCriterioEntity;
    }
}
