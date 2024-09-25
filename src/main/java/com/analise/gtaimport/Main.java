package com.analise.gtaimport;

import com.analise.gtaimport.impl.ExcelImporter;
import com.analise.gtaimport.model.DemandaEntity;
import com.analise.gtaimport.model.PlanilhaModel;
import com.analise.gtaimport.repository.UnidadeRepository;
import com.analise.gtaimport.service.UnidadeService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class Main {

    @Autowired
    private UnidadeService unidadeService;

    private List<DemandaEntity> demandaEntities = new ArrayList<>();

    public void main(String[] args) {
        ExcelImporter importer = new ExcelImporter();
        try {
            Map<String, List<PlanilhaModel>> dados = importer.importarTodasAbas(Paths.get("C:\\Users\\renildosv\\Documents\\_Developer\\planilha.xlsx"));

            for (List<PlanilhaModel> planilha : dados.values()) {
                for (PlanilhaModel planilhaModel : planilha) {
                    toDemandaEntity(planilhaModel);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DemandaEntity toDemandaEntity(PlanilhaModel planilhaModel) {
//        service
        DemandaEntity demandaEntity = new DemandaEntity();
        demandaEntity.setDataSolicitacao(planilhaModel.getDataSolicitacao());
        demandaEntity.setDocumentoSeiSolicitacao(planilhaModel.getDocseiSolicitacao());
        demandaEntity.setDescricao(planilhaModel.getDescricaoSolicitacao());
        demandaEntity.setObservacao(planilhaModel.getObservacao());
        demandaEntity.setPrevisaoEntrega(planilhaModel.getPrevisaoEntrega());
        demandaEntity.setDataEntrega(planilhaModel.getDataEntrega());
        demandaEntity.setDocumentoSeiEntrega(planilhaModel.getDocSeiEntrega());
//                    demandaEntity.setUnidade(planilhaModel.getUnidadeSolicitante());
//                    demandaEntity.setServidor(planilhaModel.getServidorDemandante());
//                    demandaEntity.setSquad(planilhaModel.getSquad());
//                    demandaEntity.setSistema(planilhaModel.getSistema());
//                    demandaEntity.setStatusDemanda(planilhaModel.getTipoDemanda());
//                    demandaEntity.setListaClassificacao(planilhaModel.);
        return demandaEntity;

    }
}
