package com.ems.email_template.model;

public interface ITemplateFactory {
    EmailTemplate createSimpleEmailTemplate(String templateName);
    EmailTemplate createSimpleEmailTemplate();
}
