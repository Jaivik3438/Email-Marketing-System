package com.ems.email_template.model;

import java.util.UUID;

public abstract class Template {
    private String templateId;
    private String templateName;

    public Template() {
        this.templateId = generateId();
    }

    public Template(String templateName) {
        this.templateId = generateId();
        this.templateName = templateName;
    }

    public Template(String templateId, String templateName) {
        this.templateId = templateId;
        this.templateName = templateName;
    }

    private String generateId() {
        return "t-" + UUID.randomUUID();
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        if (templateId != null) {
            this.templateId = templateId;
        }
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        if (templateName != null) {
            this.templateName = templateName;
        }
    }
}
