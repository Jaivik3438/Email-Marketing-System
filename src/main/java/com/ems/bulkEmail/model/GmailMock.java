package com.ems.bulkEmail.model;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

public class GmailMock implements ISendEmail {


    public boolean sendEmail(EmailDetails emailDetails) {
        final String fromEmail = "emsprojectasdc@gmail.com"; //requires valid gmail id
        final String password = "munmhsulzlibxdrf"; // correct password for gmail id
        final String toEmail = "emsasdc123@mailfence.com"; // can be any email id

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.socketFactory.port", "465"); //SSL Port
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
        props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
        props.put("mail.smtp.port", "465"); //SMTP Port

        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };

        Session session = Session.getDefaultInstance(props, auth);
        try{
            MimeMessage msg = new MimeMessage(session);
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress("no_reply@example.com", "NoReply-EMS"));

            msg.setReplyTo(InternetAddress.parse("no_reply@example.com", false));

            msg.setSubject("hello", "UTF-8");

            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            Multipart multipart = new MimeMultipart();
            BodyPart messageBodyPart = new MimeBodyPart();

            messageBodyPart.setContent("<h1>hello</h1> <img src=\"http://static.mydomain.com/images/logo.png\" alt=\"Logo\" title=\"Logo\" style=\"display:block\" width=\"200\" height=\"87\" />" ,"text/html");
            multipart.addBodyPart(messageBodyPart);
            msg.setContent(multipart);

            Transport.send(msg);

        }catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return false;
    }
}


