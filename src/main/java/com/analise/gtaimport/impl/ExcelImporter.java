package com.analise.gtaimport.impl;

import com.analise.gtaimport.model.PlanilhaModel;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelImporter {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private static final List<String> abas = List.of("ALPHA","TAU","SIGMA","DELTA","OMEGA");

    public Map<String, List<PlanilhaModel>> importarTodasAbas(Path caminhoArquivo) throws IOException {
        Map<String, List<PlanilhaModel>> dadosPorAba = new HashMap<>();

        try (FileInputStream fis = new FileInputStream(caminhoArquivo.toFile());
             Workbook workbook = new XSSFWorkbook(fis)) {

            int numeroDeAbas = workbook.getNumberOfSheets(); // Pega o número total de abas
            for (int abaIndex = 0; abaIndex < numeroDeAbas; abaIndex++) {
                Sheet sheet = workbook.getSheetAt(abaIndex);

                if (!abas.contains(sheet.getSheetName())) {
                    continue;
                }
                System.out.println("aba: " + abaIndex);
                List<PlanilhaModel> dadosAba = new ArrayList<>();

                for (int i = 1; i <= sheet.getLastRowNum(); i++) { // Pula a linha de cabeçalho
                    Row row = sheet.getRow(i);
                    PlanilhaModel model = new PlanilhaModel();
                    model.setDataSolicitacao(getLocalDateCellValue(row, 0));
                    model.setDocseiSolicitacao(getStringCellValue(row, 1));
                    model.setUnidadeSolicitante(getStringCellValue(row, 2));
                    model.setServidorDemandante(getStringCellValue(row, 3));
                    model.setSquad(getStringCellValue(row, 4));
                    model.setSistema(getStringCellValue(row, 5));
                    model.setTipoDemanda(getStringCellValue(row, 6));
                    model.setDescricaoSolicitacao(getStringCellValue(row, 7));
                    model.setObservacao(getStringCellValue(row, 8));
                    model.setStatus(getStringCellValue(row, 9));
                    model.setPrevisaoEntrega(getLocalDate2CellValue(row, 10));
                    model.setDataEntrega(getLocalDateCellValue(row, 11));
                    model.setDocSeiEntrega(getStringCellValue(row, 12));
                    model.setC1(getNumericCellValue(row, 13));
                    model.setC2_1(getNumericCellValue(row, 14));
                    model.setC2_2(getNumericCellValue(row, 15));
                    model.setC3_1(getNumericCellValue(row, 16));
                    model.setC4_1(getNumericCellValue(row, 17));
                    model.setC4_2(getNumericCellValue(row, 18));
                    model.setC4_3(getNumericCellValue(row, 19));
                    model.setC4_4(getNumericCellValue(row, 20));
                    model.setC4_5(getNumericCellValue(row,21));
                    model.setC5_1(getNumericCellValue(row, 22));
                    model.setC6_1(getNumericCellValue(row, 23));
                    model.setC6_2(getNumericCellValue(row, 24));
                    model.setC7_1(getNumericCellValue(row, 25));
                    model.setC8_1(getNumericCellValue(row, 26));
                    model.setC9_1(getNumericCellValue(row, 27));
                    model.setC10_1(getNumericCellValue(row, 28));
                    model.setScore(getNumericCellValue(row, 29));

                    if (model.getDataSolicitacao() == null && model.getSquad().equals("")) {
                        System.out.println("Aquii");
                    } else {
                        dadosAba.add(model);
                    }
                }

                dadosPorAba.put(sheet.getSheetName(), dadosAba); // Armazena os dados por aba
            }
            fis.close();
        }


        return dadosPorAba;
    }

    private String getStringCellValue(Row row, Integer cellIndex) {
        if (row == null){
            return "";
        }
        Cell cell = row.getCell(cellIndex);
        if (cell != null && cell.getCellType().equals(CellType.NUMERIC)) {
            return String.valueOf(cell.getNumericCellValue());
        }
        return cell != null ? cell.getStringCellValue() : "";
    }

    private double getNumericCellValue(Row row, Integer cellIndex) {
        if (row == null){
            return 0;
        }
        Cell cell = row.getCell(cellIndex);
        if (cell != null && !row.getCell(cellIndex).getCellType().equals(CellType.NUMERIC)) {
            return 0;
        }
        return cell != null ? cell.getNumericCellValue() : 0;
    }

//    private LocalDateTime getLocalDateCellValue(Row row, Integer cellIndex) {
//        Cell cell = row.getCell(cellIndex);
//        if (cell != null && cell.getCellType() == CellType.STRING) {
//            return LocalDateTime.parse(cell.getStringCellValue().trim(), formatter);
//        } else if (cell != null && cell.getCellType() == CellType.NUMERIC) {
//            return cell.getLocalDateTimeCellValue();
//        } else return null;
//    }

    private LocalDateTime getLocalDateCellValue(Row row, Integer cellIndex) {
        if (row == null)
            return null;
        Cell cell = row.getCell(cellIndex);

        if (cell != null) {
            if (cell.getCellType() == CellType.STRING && cell.getStringCellValue().length() >= 10) {
                String dateString = cell.getStringCellValue().trim().substring(0, 10);
                try {
                    // Tentar primeiro como LocalDateTime (data com tempo)
                    return LocalDateTime.parse(dateString, formatter);
                } catch (DateTimeParseException e) {
                    // Se falhar, tentar como LocalDate (apenas data) e definir como meia-noite
                    return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                            .atStartOfDay();
                }
            } else if (cell.getCellType() == CellType.NUMERIC) {
                // Se for uma célula numérica, retorna diretamente a data/hora
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getLocalDateTimeCellValue(); // Funciona para datas numéricas
                }
            }
        }

        return null; // Retorna null se a célula não tiver uma data válida
    }

    private LocalDate getLocalDate2CellValue(Row row, Integer cellIndex) {
        if (row == null)
            return null;
        Cell cell = row.getCell(cellIndex);
        if (cell != null && cell.getCellType() == CellType.STRING) {
            return LocalDate.parse(cell.getStringCellValue().trim(), formatter);
        } else if (cell != null && cell.getCellType() == CellType.NUMERIC) {
            return cell.getLocalDateTimeCellValue().toLocalDate();
        } else
            return null;
    }
}
