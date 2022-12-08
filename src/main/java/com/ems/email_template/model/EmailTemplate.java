package com.ems.email_template.model;

public abstract class EmailTemplate extends Template {
    private String templateSubject;
    private String templateDescription;
    private String landingPageLink;

    public EmailTemplate() {
        super();
    }

    public EmailTemplate(String templateName){
        super(templateName);
    }

    public EmailTemplate(String templateName, String templateSubject, String templateDescription, String landingPageLink) {
        super(templateName);
        this.templateSubject = templateSubject;
        this.templateDescription = templateDescription;
        this.landingPageLink = landingPageLink;
    }

    public EmailTemplate(String templateId, String templateName, String templateSubject, String templateDescription, String landingPageLink) {
        super(templateId, templateName);
        this.templateSubject = templateSubject;
        this.templateDescription = templateDescription;
        this.landingPageLink = landingPageLink;
    }

    public String getTemplateSubject() {
        return templateSubject;
    }

    public void setTemplateSubject(String templateSubject) {
        this.templateSubject = templateSubject;
    }

    public String getTemplateDescription() {
        return templateDescription;
    }

    public void setTemplateDescription(String templateDescription) {
        this.templateDescription = templateDescription;
    }

    public String getLandingPageLink() {
        return landingPageLink;
    }

    public void setLandingPageLink(String landingPageLink) {
        this.landingPageLink = landingPageLink;
    }
}
