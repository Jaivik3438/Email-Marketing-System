package com.ems.bulkEmail.buisness;

import com.ems.email_template.model.EmailTemplate;
import com.ems.email_template.model.Template;

//the class represent a simple email without any analytics
public class SimpleEmail extends Mail{

    @Override
    public void generateSubject(Template template) {
    subject=((EmailTemplate)template).getTemplateSubject();
    }

    @Override
    public void generateBody(Template template) {
        body=body+"<p>"+ ((EmailTemplate)template).getTemplateDescription()+"<p>";
    }

}
