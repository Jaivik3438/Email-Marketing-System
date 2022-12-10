package com.ems.email_template.model;

public class SimpleEmailTemplate extends EmailTemplate {

    public SimpleEmailTemplate() {
        super();
    }

    public SimpleEmailTemplate(String templateName) {
        super(templateName);
    }

    public SimpleEmailTemplate(String templateName, String templateSubject, String templateDescription, String landingPageLink) {
        super(templateName, templateSubject, templateDescription, landingPageLink);
    }

    public SimpleEmailTemplate(String templateId, String templateName, String templateSubject, String templateDescription, String landingPageLink) {
        super(templateId, templateName, templateSubject, templateDescription, landingPageLink);
    }
}
