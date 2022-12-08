package com.ems.email_template.model.template_fetcher;


import com.ems.email_template.model.template_state.TemplateState;

public interface ITemplateFetcher {
    TemplateState fetchAllTemplates();
    TemplateState fetchTemplate(String templateId);
}
