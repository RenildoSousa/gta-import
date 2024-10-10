//package com.analise.gtaimport.util;
//
//import org.springframework.context.ApplicationContextException;
//
//import javax.mail.*;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//import java.util.Properties;
//
//public class Email {
//
//
//    public static void enviar(String destinatario, String titulo, String mensagem) {
//        try {
//            enviar(destinatario, titulo, mensagem, "noreply@tce.to.gov.br");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void enviar(String destinatario, String titulo, String mensagem, String from) throws ApplicationContextException {
//        String d_uname = "regularizacaocnpjcadun@gmail.com",
//                d_password = "s1c0ptc3",
//                d_host = "smtp.gmail.com";
//        int d_port = 465; // 465,587
//        System.setProperty("java.net.useSystemProxies", "false");
//
//
//        Properties props = new Properties();
//        props.put("mail.smtp.host", d_host);
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.debug", "true");
//        props.put("mail.smtp.ssl.enable", "true");
//        props.put("mail.smtp.ssl.trust", d_host);
//        props.put("mail.smtp.starttls.enable", "false");
//        props.put("mail.smtp.port", d_port);
//
//        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication("caduntce@gmail.com", "kftkmyuarqeogfeu");
//            }
//        });
//
//        try {
//            Message message = new MimeMessage(session);
//
//            message.setFrom(new InternetAddress(from));
//            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
//            message.setSubject(titulo);
//            message.setText(mensagem);
//
//            Transport.send(message);
//
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//    }
//}
