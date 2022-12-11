package com.ems.bulkEmail.model;

import com.ems.email_template.model.Template;

public abstract class Mail {
    String pixelId;
    String clickId;

    String subject;
    String body;


    public abstract void generateSubject(Template template);
    public abstract void generateBody(Template template);

}
