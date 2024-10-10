package com.analise.gtaimport.impl;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class DownloadExcel {
    public static void main(String[] args) {
        String url = "https://tcetobr.sharepoint.com/:x:/s/CoordDesenvolvimentoSistemas/Ebh19XSrtNhMuXMkGR3GS3sBJ6NjR1XOnwwt-Vl4MFEmNw?e=eitvKa";
        String destinationPath = "C:\\Users\\renildosv\\Documents\\20230101230410\\Demandas_CODES.xlsx"; // Caminho onde o arquivo será salvo

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);

            String username = "renildosv@tceto.tc.br"; // Substitua pelo seu nome de usuário
            String password = "@C3550renildo"; // Substitua pela sua senha

            String auth = username + ":" + password;
            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));
            request.setHeader("Authorization", "Basic " + encodedAuth);
            // Adicione autenticação aqui, se necessário
            // request.setHeader("Authorization", "Bearer " + yourAccessToken);

            HttpResponse response = client.execute(request);
            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode == 200) { // Verifica se a resposta foi bem-sucedida
                try (InputStream inputStream = response.getEntity().getContent();
                     FileOutputStream outputStream = new FileOutputStream(destinationPath)) {

                    byte[] buffer = new byte[8192]; // Buffer aumentado para 8 KB
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                }
                System.out.println("Arquivo baixado com sucesso.");
            } else {
                System.out.println("Falha ao baixar o arquivo. Código de status: " + statusCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
