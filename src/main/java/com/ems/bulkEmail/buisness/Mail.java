package com.ems.bulkEmail.buisness;

import com.ems.email_template.model.Template;

public abstract class Mail {
    public String pixelId;
    public String clickId;

    public String subject;
    public String body;

    public Mail(){
        body="";
    }
    public abstract void generateSubject(Template template);
    public abstract void generateBody(Template template);

}
