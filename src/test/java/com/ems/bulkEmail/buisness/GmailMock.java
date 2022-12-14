package com.ems.bulkEmail.buisness;

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
        if (emailDetails.id=="1"){
            return false;

        }

        return true;
    }
}


