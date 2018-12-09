/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author KeylorSk8
 */
public class Correo {
    public static boolean enviarCorreo(String destinatario,String Asunto,String Mensaje) {
        boolean enviado = false;

        try {
            String host = "smtp.gmail.com";
            Properties prop = System.getProperties();
            prop.put("mail.smtp.starttls.enable", true);
            prop.put("mail.smtp.host",host);
            prop.put("mail.smpt.user","DEASUTNCR@gmail.com");
            prop.put("mail.smtp.password", "deasutn123456");
            prop.put("mail.smpt","deasutn123456");
            prop.put("mail.smtp.port",587);
            prop.put("mail.smpt.auth", true);
            
            Session session = Session.getDefaultInstance(prop,null);
            
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("DEASUTNCR@gmail.com"));
            message.setRecipient(Message.RecipientType.TO,new InternetAddress(destinatario));
            message.setSubject(Asunto);
            message.setContent(Mensaje,"text/html");
            
            Transport transport = session.getTransport("smtp");
            transport.connect(host,"DEASUTNCR@gmail.com","deasutn123456");
            transport.sendMessage(message,message.getRecipients(Message.RecipientType.TO));
            transport.close();
            enviado = true;
        } catch (MessagingException e) {
            System.out.println(e.toString());
            System.out.println(e.getMessage());
        }
        finally{
            return enviado;
        }
    }
}
