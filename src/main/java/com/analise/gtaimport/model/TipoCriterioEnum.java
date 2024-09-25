package com.analise.gtaimport.model;

public enum TipoCriterioEnum {
    AUTOMATICO('A'),
    MANUAL('M');
    
    private final char tipo;
    
    TipoCriterioEnum(char tipo) {
        this.tipo = tipo;
    }
    
    public char getTipo() {
        return tipo;
    }
}
