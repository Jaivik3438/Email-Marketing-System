package com.ems.email_template.model;

public interface ITemplateFactory {
    EmailTemplate createSimpleEmailTemplate(String templateName);
    EmailTemplate createSimpleEmailTemplate();
    EmailTemplate createSimpleEmailTemplate(String id, String templateName, String templateSubject, String templateDescription, String landingPageLink);
}
