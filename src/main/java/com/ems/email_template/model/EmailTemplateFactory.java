package com.ems.email_template.model;

public class EmailTemplateFactory implements ITemplateFactory {

    @Override
    public EmailTemplate createSimpleEmailTemplate(String templateName) {
        return new SimpleEmailTemplate(templateName);
    }

    @Override
    public EmailTemplate createSimpleEmailTemplate() {
        return new SimpleEmailTemplate();
    }


}
