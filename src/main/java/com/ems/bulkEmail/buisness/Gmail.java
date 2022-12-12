package com.ems.bulkEmail.buisness;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.Properties;

public class Gmail implements ISendEmail{
    Properties props;
    String fromEmail;
    String password;
    EmailDetails emailDetails;

    Authenticator auth;
    Gmail(String fromEmail,String password){
        props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.socketFactory.port", "465"); //SSL Port
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
        props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
        props.put("mail.smtp.port", "465"); //SMTP Port

        this.fromEmail=fromEmail;
        this.password=password;


        auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
    }
    @Override
    public boolean sendEmail(EmailDetails emailDetails) {


        Session session = Session.getDefaultInstance(props, auth);
        try{
            MimeMessage msg = new MimeMessage(session);
            msg=setMessage(msg);
            Transport.send(msg);

        }catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    private MimeMessage setMessage(MimeMessage msg) throws Exception{
        msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
        msg.addHeader("format", "flowed");
        msg.addHeader("Content-Transfer-Encoding", "8bit");

        msg.setFrom(new InternetAddress("no_reply@example.com", "NoReply-EMS"));

        msg.setReplyTo(InternetAddress.parse("no_reply@example.com", false));

        msg.setSubject(emailDetails.mail.subject, "UTF-8");

        msg.setSentDate(new Date());

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailDetails.subscriber.sub_email, false));
        Multipart multipart = new MimeMultipart();
        BodyPart messageBodyPart = new MimeBodyPart();

        messageBodyPart.setContent(emailDetails.mail.body, "text/html");
        multipart.addBodyPart(messageBodyPart);
        msg.setContent(multipart);
        return msg;
    }
}
