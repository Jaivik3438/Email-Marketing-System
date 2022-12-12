package com.ems.bulkEmail.buisness;

import com.ems.email_template.model.Template;

public abstract class SimpleEmailDecorator extends Mail {
    protected  Mail wrappedMail;
    SimpleEmailDecorator(Mail wrappedMail){
         this.wrappedMail=wrappedMail;
    }
    public  void generateSubject(Template template){
        wrappedMail.generateSubject(template);
    }
    public  void generateBody(Template template){
        wrappedMail.generateBody(template);
    }


}
