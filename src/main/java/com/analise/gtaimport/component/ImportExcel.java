package com.analise.gtaimport.component;

import com.analise.gtaimport.impl.DownloadExcel;
import com.analise.gtaimport.impl.ExcelImporter;
import com.analise.gtaimport.model.*;
import com.analise.gtaimport.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ImportExcel implements CommandLineRunner {
    @Autowired
    private UnidadeService unidadeService;

    @Autowired
    private ServidorService servidorService;

    @Autowired
    private SquadService squadService;

    @Autowired
    private SistemaService sistemaService;

    @Autowired
    private StatusDemandaService statusDemandaService;

    @Autowired
    private DemandaService DemandaService;

    @Autowired
    private ItemCriterioService itemCriterioService;

    private List<DemandaEntity> demandaEntities = new ArrayList<>();
    @Autowired
    private DemandaService demandaService;
    @Autowired
    private ClassificacaoService classificacaoService;

    @Autowired
    private EmailService emailService;

    private static String emailReport = "codes@tceto.tc.br";

    @Override
    public void run(String... args) throws Exception {
        main(args);
    }

    public void main(String[] args) {

        ExcelImporter importer = new ExcelImporter();
        try {
//            DownloadExcel.main(args);
            String filePath = "W:\\CODES\\Priorização de demandas\\Demandas CODES.xlsx";

            if (Files.exists(Path.of(filePath))) {
                System.out.println("Arquivo existe.");
            } else {

//                Email.enviar(emailReport, "Arquivo não existe.", "Hoje :" + new Date().toStringDateAndHour());
                while (!Files.exists(Path.of(filePath))) {
                    emailService.enviarEmail(emailReport, "Planilha priorização de demandas", "Por favor ponha o arquivo na pasta pública \\CODES\\Priorização de demandas");
                    System.out.println("Arquivo não existe.");
                    Thread.sleep(30 * 60 * 1000);
                }
            }
            classificacaoService.deleteAll();
            demandaService.deleteAll();
            Map<String, List<PlanilhaModel>> dados = importer.importarTodasAbas(Paths.get(filePath));

            for (List<PlanilhaModel> planilha : dados.values()) {
                for (PlanilhaModel planilhaModel : planilha) {
//                    System.out.println(planilhaModel);
                    demandaEntities.add(toDemandaEntity(planilhaModel));
//                    demandaEntities.stream().filter(demandaEntity -> demandaEntity.getUnidade() == null).toList()
//                    demandaEntities.stream().filter(demandaEntity -> demandaEntity.getServidor() == null).toList()
                }
            }
            for (DemandaEntity demandaEntity : demandaEntities) {
                demandaService.salvar(demandaEntity);
            }
            apagarFile();
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
        UnidadeEntity unidadeEntity = new UnidadeEntity();
        unidadeEntity.setNome(planilhaModel.getUnidadeSolicitante());
        unidadeEntity.setSigla(planilhaModel.getUnidadeSolicitante());
        demandaEntity.setUnidade(unidadeService.findByName(planilhaModel.getUnidadeSolicitante().trim(), planilhaModel.getUnidadeSolicitante().trim()) == null ?
                unidadeService.salvar(unidadeEntity) : unidadeService.findByName(planilhaModel.getUnidadeSolicitante().trim(), planilhaModel.getUnidadeSolicitante().trim()));

        ServidorEntity servidorEntity = new ServidorEntity();
        servidorEntity.setNome(planilhaModel.getServidorDemandante().trim());
        demandaEntity.setServidor(
                servidorService.findByName(planilhaModel.getServidorDemandante().trim()) == null ?
                        servidorService.salvar(servidorEntity) : servidorService.findByName(planilhaModel.getServidorDemandante().trim()));

        demandaEntity.setSquad(squadService.findByName(planilhaModel.getSquad().trim()));

        SistemaEntity sistemaEntity = new SistemaEntity();
        sistemaEntity.setNome(planilhaModel.getSistema().trim());
        demandaEntity.setSistema(sistemaService.findByName(planilhaModel.getSistema().trim()) == null ? sistemaService.salvar(sistemaEntity) : sistemaService.findByName(planilhaModel.getSistema().trim()));

        StatusDemandaEntity statusDemandaEntity = new StatusDemandaEntity();
        statusDemandaEntity.setNome(planilhaModel.getStatus().trim());

        demandaEntity.setStatusDemanda(
                statusDemandaService.findByName(planilhaModel.getStatus().trim()) == null ?
                        statusDemandaService.salvar(statusDemandaEntity) : statusDemandaService.findByName(planilhaModel.getStatus().trim()));

        ItemCriterioEntity itemCriterioc1_1 = itemCriterioService.findByName("C1.1", planilhaModel.getTipoDemanda().trim());
        ItemCriterioEntity itemCriterioc2_1 = itemCriterioService.findByScore("C2.1", planilhaModel.getC2_1());
        ItemCriterioEntity itemCriterioc2_2 = itemCriterioService.findByScore("C2.2", planilhaModel.getC2_2());
        ItemCriterioEntity itemCriterioc3_1 = itemCriterioService.findByScore("C3.1", planilhaModel.getC3_1());
        ItemCriterioEntity itemCriterioc4_1 = itemCriterioService.findByScore("C4.1", planilhaModel.getC4_1());
        ItemCriterioEntity itemCriterioc4_2 = itemCriterioService.findByScore("C4.2", planilhaModel.getC4_2());
        ItemCriterioEntity itemCriterioc4_3 = itemCriterioService.findByScore("C4.3", planilhaModel.getC4_3());
        ItemCriterioEntity itemCriterioc4_4 = itemCriterioService.findByScore("C4.4", planilhaModel.getC4_4());
        ItemCriterioEntity itemCriterioc4_5 = itemCriterioService.findByScore("C4.5", planilhaModel.getC4_5());
        ItemCriterioEntity itemCriterioc5_1 = itemCriterioService.findByScore("C5.1", planilhaModel.getC5_1());
        ItemCriterioEntity itemCriterioc6_1 = itemCriterioService.findByScore("C6.1", planilhaModel.getC6_1());
        ItemCriterioEntity itemCriterioc6_2 = itemCriterioService.findByScore("C6.2", planilhaModel.getC6_2());
        ItemCriterioEntity itemCriterioc7_1 = itemCriterioService.findByScore("C7.1", planilhaModel.getC7_1());
        ItemCriterioEntity itemCriterioc8_1 = itemCriterioService.findByScore("C8.1", planilhaModel.getC8_1());
//        ItemCriterioEntity itemCriterioc9_1 = itemCriterioService.findByScore("C9.1", 0.1);
//        ItemCriterioEntity itemCriterioc10_1 = itemCriterioService.findByScore("C10.1", planilhaModel.getC10_1());

        List<ItemCriterioEntity> itemCriterioEntityList = new ArrayList<>();
        itemCriterioEntityList.add(itemCriterioc1_1);
        itemCriterioEntityList.add(itemCriterioc2_1);
        itemCriterioEntityList.add(itemCriterioc2_2);
        itemCriterioEntityList.add(itemCriterioc3_1);
        itemCriterioEntityList.add(itemCriterioc4_1);
        itemCriterioEntityList.add(itemCriterioc4_2);
        itemCriterioEntityList.add(itemCriterioc4_3);
        itemCriterioEntityList.add(itemCriterioc4_4);
        itemCriterioEntityList.add(itemCriterioc4_5);
        itemCriterioEntityList.add(itemCriterioc5_1);
        itemCriterioEntityList.add(itemCriterioc6_1);
        itemCriterioEntityList.add(itemCriterioc6_2);
        itemCriterioEntityList.add(itemCriterioc7_1);
        itemCriterioEntityList.add(itemCriterioc8_1);

        demandaEntity.setListaClassificacao(itemCriterioEntityList);
//                    demandaEntity.setListaClassificacao(planilhaModel.);
        return demandaEntity;

    }

    public void apagarFile(){
        File file = new File("W:\\CODES\\Priorização de demandas\\Demandas CODES.xlsx");

        if (isFileInUse(file))
            emailService.enviarEmail("renildosousa75@gmail.com", "Não foi possivel apagar a planilha","Alguém está com o arquivo W:\\CODES\\Priorização de demandas\\Demandas CODES.xlsx aberto!");

        if (file.canWrite()) {
            if (file.delete()) {
                System.out.println("Arquivo deletado com sucesso.");
            } else {
                System.out.println("Falha ao deletar o arquivo.");
            }
        } else {
            System.out.println("Sem permissão para deletar o arquivo.");
        }
    }

    // Método para verificar se o arquivo está em uso
    public static boolean isFileInUse(File file) {
        try (FileOutputStream fos = new FileOutputStream(file, true)) {
            // Se o arquivo puder ser aberto para escrita, ele não está em uso
            return false;
        } catch (IOException e) {
            // Se ocorrer uma exceção, o arquivo está em uso
            return true;
        }
    }
}
